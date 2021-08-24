package ru.surpavel.bugtrackingsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.bugtrackingsystem.dto.TaskDTO;
import ru.surpavel.bugtrackingsystem.dto.UserDTO;
import ru.surpavel.bugtrackingsystem.entity.Project;
import ru.surpavel.bugtrackingsystem.entity.Task;
import ru.surpavel.bugtrackingsystem.entity.User;
import ru.surpavel.bugtrackingsystem.service.ProjectService;
import ru.surpavel.bugtrackingsystem.service.ResourceNotFoundException;
import ru.surpavel.bugtrackingsystem.service.TaskService;
import ru.surpavel.bugtrackingsystem.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.surpavel.bugtrackingsystem.controller.ProjectController.PROJECT_ID;
import static ru.surpavel.bugtrackingsystem.controller.UserController.USER_ID;

@RestController
public class TaskController {

    public static final String TASK_ID = "TaskId ";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjectController projectController;

    @Autowired
    private UserController userController;

    @PostMapping("/projects/{projectId}/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TaskDTO createTask(@PathVariable(value = "projectId") Long projectId,
                              @PathVariable(value = "userId") Long userId,
                              @RequestBody TaskDTO taskDTO) {
        if (!projectService.existsById(projectId)) {
            throw new ResourceNotFoundException(PROJECT_ID + projectId);
        }
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException(USER_ID + userId);
        }
        Task task = convertToEntity(taskDTO);
        Project project = projectController.getProject(projectId);
        task.setProject(project);
        User user = userController.getUser(userId);
        task.setUser(user);
        Task taskCreated = taskService.save(task);
        return convertToDTO(taskCreated);
    }

    @GetMapping("/tasks")
    public List<TaskDTO> findAllTasks() {
        List<Task> tasks = taskService.findAll();
        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @GetMapping("/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO findTaskById(@PathVariable Long taskId) {
        Task task = getTask(taskId);
        return convertToDTO(task);

    }

    @PutMapping("/projects/{projectId}/users/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(@PathVariable(value = "projectId") Long projectId,
                           @PathVariable(value = "userId") Long userId,
                           @PathVariable(value = "taskId") Long taskId,
                           @RequestBody TaskDTO taskDTO) {
        if (!projectService.existsById(projectId)) {
            throw new ResourceNotFoundException(PROJECT_ID + projectId);
        }
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException(USER_ID + userId);
        }
        return taskService.findById(taskId).map(task -> {
            task.setTheme(taskDTO.getTheme());
            task.setTaskType(taskDTO.getTaskType());
            task.setPriority(taskDTO.getPriority());
            task.setDescription(taskDTO.getDescription());
            task.setProject(projectController.getProject(projectId));
            task.setUser(userController.getUser(userId));
            return taskService.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException(TASK_ID + taskId));
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long taskId) {
        return taskService.findById(taskId).map(task -> {
            taskService.delete(task);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(TASK_ID + taskId));
    }

    protected TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
        taskDTO.setTaskType(task.getTaskType());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setTheme(task.getTheme());
        UserDTO userDTO = userController.convertToDTO(task.getUser());
        taskDTO.setUserDTO(userDTO);
        return taskDTO;
    }

    protected Task convertToEntity(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setTaskType(taskDTO.getTaskType());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setTheme(taskDTO.getTheme());
        User user = userController.convertToEntity(taskDTO.getUserDTO());
        task.setUser(user);
        return task;
    }

    private Task getTask(@PathVariable Long taskId) {
        Optional<Task> optionalTask = taskService.findById(taskId);
        return optionalTask.orElseThrow(()
                -> new ResourceNotFoundException(PROJECT_ID + taskId));
    }
}
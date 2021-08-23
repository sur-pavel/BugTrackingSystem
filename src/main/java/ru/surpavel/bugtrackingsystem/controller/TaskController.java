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
import ru.surpavel.bugtrackingsystem.repository.ProjectRepository;
import ru.surpavel.bugtrackingsystem.repository.ResourceNotFoundException;
import ru.surpavel.bugtrackingsystem.repository.TaskRepository;
import ru.surpavel.bugtrackingsystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.surpavel.bugtrackingsystem.controller.ProjectController.PROJECT_ID;
import static ru.surpavel.bugtrackingsystem.controller.UserController.USER_ID;

@RestController
public class TaskController {

    public static final String TASK_ID = "TaskId ";
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserController userController;

    @PostMapping("/projects/{projectId}/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TaskDTO createTask(@PathVariable(value = "projectId") Long projectId,
                              @PathVariable(value = "userId") Long userId,
                              @RequestBody TaskDTO taskDTO) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException(PROJECT_ID + projectId);
        }
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(USER_ID + userId);
        }
        Task task = convertToEntity(taskDTO);
        Project project = getProject(projectId);
        task.setProject(project);
        User user = getUser(userId);
        task.setUser(user);
        Task taskCreated = taskRepository.save(task);
        return convertToDTO(taskCreated);
    }

    private User getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(()
                -> new ResourceNotFoundException(USER_ID + userId));
    }

    private Project getProject(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        return optionalProject.orElseThrow(()
                -> new ResourceNotFoundException(PROJECT_ID + projectId));
    }

    @GetMapping("/tasks")
    public List<TaskDTO> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @GetMapping("/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO findTaskById(@PathVariable Long taskId) {
        Task task = getTask(taskId);
        return convertToDTO(task);

    }

    @PutMapping("/projects/{projectId}/users/{userId}/tasks/{taskId}")
    public Task updateTask(@PathVariable(value = "projectId") Long projectId,
                           @PathVariable(value = "userId") Long userId,
                           @PathVariable(value = "taskId") Long taskId,
                           TaskDTO taskDTO) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException(PROJECT_ID + projectId);
        }
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(USER_ID + userId);
        }
        return taskRepository.findById(taskId).map(task -> {
            task.setTheme(taskDTO.getTheme());
            task.setTaskType(taskDTO.getTaskType());
            task.setPriority(taskDTO.getPriority());
            task.setDescription(taskDTO.getDescription());
            task.setProject(getProject(projectId));
            task.setUser(getUser(userId));
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException(TASK_ID + taskId));
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long taskId) {
        return taskRepository.findById(taskId).map(task -> {
            taskRepository.delete(task);
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
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        return optionalTask.orElseThrow(()
                -> new ResourceNotFoundException(PROJECT_ID + taskId));
    }

}
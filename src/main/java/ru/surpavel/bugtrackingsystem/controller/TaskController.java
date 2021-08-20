package ru.surpavel.bugtrackingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.bugtrackingsystem.entity.Task;
import ru.surpavel.bugtrackingsystem.repository.ProjectRepository;
import ru.surpavel.bugtrackingsystem.repository.ResourceNotFoundException;
import ru.surpavel.bugtrackingsystem.repository.TaskRepository;
import ru.surpavel.bugtrackingsystem.repository.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/projects/{projectId}/users/{userId}/tasks")
    public Task createTask(@PathVariable(value = "projectId") Long projectId,
                           @PathVariable(value = "userId") Long userId, @Valid Task task) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("ProjectId " + projectId);
        }
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId);
        }
        task.setProject(projectRepository.findById(projectId).get());
        task.setUser(userRepository.findById(userId).get());
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Page<Task> findAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @GetMapping("/tasks/{taskId}")
    public Optional<Task> findTaskById(@PathVariable Long taskId) {
        return taskRepository.findById(taskId);
    }

    @PutMapping("/projects/{projectId}/users/{userId}/tasks/{taskId}")
    public Task updateTask(@PathVariable(value = "projectId") Long projectId,
                           @PathVariable(value = "userId") Long userId, @PathVariable(value = "taskId") Long taskId,
                           @Valid Task taskRequest) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("ProjectId " + projectId);
        }
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId);
        }
        return taskRepository.findById(taskId).map(task -> {
            task.setTheme(taskRequest.getTheme());
            task.setTaskType(taskRequest.getTaskType());
            task.setPriority(taskRequest.getPriority());
            task.setDescription(taskRequest.getDescription());
            task.setProject(projectRepository.findById(projectId).get());
            task.setUser(userRepository.findById(userId).get());
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("TaskId " + taskId));
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long taskId) {
        return taskRepository.findById(taskId).map(task -> {
            taskRepository.delete(task);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("TaskId " + taskId));
    }
}
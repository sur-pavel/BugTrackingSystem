package ru.surpavel.bugtrackingsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.bugtrackingsystem.dto.ProjectDTO;
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

@RestController
public class ProjectController {

    public static final String PROJECT_ID = "ProjectId ";
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private TaskController taskController;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/projects")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        Project projectCreated = projectRepository.save(project);
        return convertToDTO(projectCreated);
    }

    @GetMapping("/projects")
    @ResponseBody
    public List<ProjectDTO> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO findProjectById(@PathVariable Long projectId) {
        Project project = getProject(projectId);
        return convertToDTO(project);
    }

    private Project getProject(@PathVariable Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        return optionalProject.orElseThrow(()
                -> new ResourceNotFoundException(PROJECT_ID + projectId));
    }

    @GetMapping("/projects/{projectId}/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAllProjectUsers(@PathVariable Long projectId) {
        Project project = getProject(projectId);
        List<User> users = userRepository.findByProjectId(project.getId());
        return users.stream().map(userController::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/projects/{projectId}/tasks")
    public List<TaskDTO> findAllProjectTasks(@PathVariable Long projectId) {
        Project project = getProject(projectId);
        List<Task> tasks = taskRepository.findByProjectId(project.getId());
        return tasks.stream().map(taskController::convertToDTO).collect(Collectors.toList());

    }

    @PutMapping("/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long projectId) {
        return projectRepository.findById(projectId).map(project -> {
            project.setTitle(projectDTO.getTitle());
            return convertToDTO(projectRepository.save(project));
        }).orElseThrow(() -> new ResourceNotFoundException(PROJECT_ID + projectId));
    }

    @DeleteMapping("/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteProject(@PathVariable Long projectId) {
        return projectRepository.findById(projectId).map(project -> {
            projectRepository.delete(project);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(PROJECT_ID + projectId));
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
        projectDTO.setTitle(project.getTitle());
        return projectDTO;
    }

    private Project convertToEntity(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
        project.setTitle(projectDTO.getTitle());
        return project;
    }
}

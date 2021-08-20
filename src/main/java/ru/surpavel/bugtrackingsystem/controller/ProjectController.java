package ru.surpavel.bugtrackingsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.bugtrackingsystem.dto.ProjectDTO;
import ru.surpavel.bugtrackingsystem.entity.Project;
import ru.surpavel.bugtrackingsystem.entity.Task;
import ru.surpavel.bugtrackingsystem.entity.User;
import ru.surpavel.bugtrackingsystem.repository.ProjectRepository;
import ru.surpavel.bugtrackingsystem.repository.ResourceNotFoundException;
import ru.surpavel.bugtrackingsystem.repository.TaskRepository;
import ru.surpavel.bugtrackingsystem.repository.UserRepository;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/projects")
    public Project createProject(@Valid Project project) {

        return projectRepository.save(project);
    }

    @GetMapping("/projects")
    public List<Project> findAllProjects(Pageable pageable) {
        return projectRepository.findAll();
    }

    @GetMapping("/projects/{projectId}")
    public Optional<Project> findProjectById(@PathVariable Long projectId) {
        return projectRepository.findById(projectId);
    }

    @GetMapping("/projects/{projectId}/users")
    public List<User> findAllProjectUsers(@PathVariable(value = "projectId") Long projectId, Pageable pageable) {
        return userRepository.findByProjectId(projectId);
    }

    @GetMapping("/projects/{projectId}/tasks")
    public List<Task> findAllProjectTasks(@PathVariable(value = "projectId") Long projectId, Pageable pageable) {
        return taskRepository.findByProjectId(projectId);
    }

    @PutMapping("/projects/{projectId}")
    public Project updateProject(@PathVariable Long projectId, @Valid Project projectRequest) {
        return projectRepository.findById(projectId).map(project -> {
            project.setTitle(projectRequest.getTitle());
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("ProjectId " + projectId));
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long projectId) {
        return projectRepository.findById(projectId).map(project -> {
            projectRepository.delete(project);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ProjectId " + projectId));
    }

    private ProjectDTO convertToDto(Project project) {
        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
        projectDTO.setTitle(project.getTitle());
        return projectDTO;
    }

    private Project convertToEntity(ProjectDTO projectDTO) throws ParseException {
        Project project = modelMapper.map(projectDTO, Project.class);
        project.setTitle(projectDTO.getTitle());
        return project;
    }
}
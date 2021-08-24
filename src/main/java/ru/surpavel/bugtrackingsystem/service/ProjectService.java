package ru.surpavel.bugtrackingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surpavel.bugtrackingsystem.entity.Project;
import ru.surpavel.bugtrackingsystem.entity.Task;
import ru.surpavel.bugtrackingsystem.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IService<Project> {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Override
    public Project save(Project entity) {
        return projectRepository.save(entity);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void delete(Project entity) {
        Long projectId = entity.getId();
        List<Task> tasks = taskService.findByProjectId(projectId);
        if (tasks.isEmpty()) {
            projectRepository.delete(entity);
        } else {
            throw new ResourceNotEmptyException("Project tasks" + tasks);
        }
    }
}

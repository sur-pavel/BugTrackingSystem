package ru.surpavel.bugtrackingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surpavel.bugtrackingsystem.entity.Task;
import ru.surpavel.bugtrackingsystem.entity.User;
import ru.surpavel.bugtrackingsystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskService taskService;

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User entity) {
        List<Task> tasks = taskService.findByUserId(entity.getId());
        boolean hadOtherUsers = false;
        Long taskId = 0L;
        for (Task task : tasks) {
            taskId = task.getId();
            List<User> users = userRepository.findByTaskId(taskId);
            if (users.size() > 2) {
                hadOtherUsers = true;
                break;
            }
        }
        if (!hadOtherUsers) {
            userRepository.delete(entity);
        } else {
            throw new ResourceNotEmptyException("Task " + taskId + " users");
        }
    }

    public List<User> findByProjectId(Long projectId) {
        return userRepository.findByProjectId(projectId);
    }
}

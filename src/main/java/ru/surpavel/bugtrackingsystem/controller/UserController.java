package ru.surpavel.bugtrackingsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.bugtrackingsystem.dto.UserDTO;
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
public class UserController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/users")
    public User createUser(@Valid User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @GetMapping("/users/{userId}")
    public Optional<User> findUserById(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @GetMapping("/users/{userId}/tasks")
    public List<Task> findUserTasks(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        return taskRepository.findByUserId(userId);
    }

    @PutMapping("/projects/{projectId}/users/{userId}")
    public User updateUser(@PathVariable(value = "projectId") Long projectId,
                           @PathVariable(value = "userId") Long userId, @Valid User userRequest) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId);
        }
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("ProjectId " + projectId);
        }
        userRequest.setProject(projectRepository.findById(projectId).get());
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId));
    }

    protected UserDTO convertToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }

    protected User convertToEntity(UserDTO userDTO) throws ParseException {
        User user = modelMapper.map(userDTO, User.class);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }
}
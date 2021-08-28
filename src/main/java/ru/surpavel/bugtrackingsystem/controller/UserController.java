package ru.surpavel.bugtrackingsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.bugtrackingsystem.dto.UserDTO;
import ru.surpavel.bugtrackingsystem.entity.User;
import ru.surpavel.bugtrackingsystem.entity.Task;
import ru.surpavel.bugtrackingsystem.entity.User;
import ru.surpavel.bugtrackingsystem.service.ProjectService;
import ru.surpavel.bugtrackingsystem.service.ResourceNotFoundException;
import ru.surpavel.bugtrackingsystem.service.TaskService;
import ru.surpavel.bugtrackingsystem.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    public static final String USER_ID = "UserId ";
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/users")
    public UserDTO createUser(@Valid User user) {
        return userService.save(user);
    }

    @GetMapping("/users")
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<UserDTO> findUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping("/users/{userId}/tasks")
    public List<Task> findUserTasks(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        return taskService.findByUserId(userId);
    }

    @PutMapping("/users/{userId}")
    public UserDTO updateUser(@PathVariable(value = "userId") Long userId,
                           @Valid User userRequest) {
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException(USER_ID + userId);
        }
        return userService.findById(userId).map(user -> {
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            return userService.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException(USER_ID + userId));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        return userService.findById(userId).map(user -> {
            userService.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(USER_ID + userId));
    }

    protected UserDTO convertToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }

    protected User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }

    protected User getUser(Long userId) {
        Optional<User> optionalUser = userService.findById(userId);
        return optionalUser.orElseThrow(()
                -> new ResourceNotFoundException(USER_ID + userId));
    }
}

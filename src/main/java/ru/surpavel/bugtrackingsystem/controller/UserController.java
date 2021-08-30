package ru.surpavel.bugtrackingsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.bugtrackingsystem.dto.TaskDTO;
import ru.surpavel.bugtrackingsystem.dto.UserDTO;
import ru.surpavel.bugtrackingsystem.entity.Task;
import ru.surpavel.bugtrackingsystem.entity.User;
import ru.surpavel.bugtrackingsystem.service.ResourceNotFoundException;
import ru.surpavel.bugtrackingsystem.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    public static final String USER_ID = "UserId ";

    @Autowired
    private UserService userService;
    @Autowired
    private TaskController taskController;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDTO(userService.save(user));
    }

    @GetMapping("/users")
    @ResponseBody
    public List<UserDTO> findAllUsers() {
        List<User> users = userService.findAll();
        return users.stream().map(this::convertToDTO).
                collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findUserById(@PathVariable Long userId) {
        User user = getUser(userId);
        return convertToDTO(user);
    }

    @GetMapping("/users/{userId}/tasks")
    public List<TaskDTO> findUserTasks(@PathVariable Long userId) {
        List<Task> tasks = taskController.findByUserId(userId);
        return tasks.stream().map(taskController::convertToDTO).
                collect(Collectors.toList());
    }

    @PutMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@RequestBody UserDTO userDTO,
                              @PathVariable Long userId) {
        return userService.findById(userId).map(user -> {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            return convertToDTO(userService.save(user));
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

    public List<User> findByProjectId(Long id) {
        return userService.findByProjectId(id);
    }

    public boolean existsById(Long userId) {
        return userService.existsById(userId);
    }
}

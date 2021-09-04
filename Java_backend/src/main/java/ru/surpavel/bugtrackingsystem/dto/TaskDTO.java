package ru.surpavel.bugtrackingsystem.dto;

public class TaskDTO {

    private String theme;

    private String taskType;

    private int priority;

    private String description;

    private UserDTO user;

    private ProjectDTO project;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO getUserDTO() {
        return user;
    }

    public void setUserDTO(UserDTO user) {
        this.user = user;
    }

    public ProjectDTO getProjectDTO() {
        return project;
    }

    public void setProjectDTO(ProjectDTO project) {
        this.project = project;
    }
}

package ru.surpavel.bugtrackingsystem.dto;

public class UserDTO {

    private String firstName;

    private String lastName;

    private ProjectDTO project;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ProjectDTO getProjectDTO() {
        return project;
    }

    public void setProjectDTO(ProjectDTO project) {
        this.project = project;
    }
}


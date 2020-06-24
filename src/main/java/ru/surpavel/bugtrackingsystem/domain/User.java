package ru.surpavel.bugtrackingsystem.domain;

import java.util.List;

public class User extends TrackedObject{

    private String FirstName; 
    private String LastName;
    private List<Task> tasks;
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

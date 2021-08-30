package ru.surpavel.bugtrackingsystem.dto;

public class ProjectDTO {
    private Long id;
    private String title;
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setId(Long id) {
        this.id = id;
    }

}

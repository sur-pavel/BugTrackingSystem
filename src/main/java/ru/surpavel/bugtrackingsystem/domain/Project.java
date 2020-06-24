package ru.surpavel.bugtrackingsystem.domain;

public class Project extends TrackedObject {

    private String file;

    public Project(String file) {
        super();
        this.setFile(file);
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    
}

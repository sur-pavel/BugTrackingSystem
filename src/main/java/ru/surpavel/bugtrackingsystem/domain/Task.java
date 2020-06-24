package ru.surpavel.bugtrackingsystem.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tasks")
@XmlRootElement
public class Task  extends TrackedObject{
    private Project project;
    private User user;
    public Task(Project project, User user) {
        super();
        this.setProject(project);
        this.setUser(user);
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    

}

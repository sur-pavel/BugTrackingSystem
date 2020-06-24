package ru.surpavel.bugtrackingsystem.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tasks")
@XmlRootElement
public class Task  extends TrackedObject{
    public Task(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    

}

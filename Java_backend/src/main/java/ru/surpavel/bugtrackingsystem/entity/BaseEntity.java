package ru.surpavel.bugtrackingsystem.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public abstract class BaseEntity {
    private String creationDate;

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.creationDate = dtf.format(LocalDateTime.now());
    }
}

package ru.surpavel.bugtrackingsystem.dao;

import java.util.List;

import ru.surpavel.bugtrackingsystem.domain.Project;

public interface ProjectDao {

    Project create(Project project);

    List<Project> findAll();

    Project findById(int id);

    Project update(Project project);

    void delete(int id);

}

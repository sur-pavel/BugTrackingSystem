package ru.surpavel.bugtrackingsystem.dao;

import java.util.List;

import ru.surpavel.bugtrackingsystem.domain.Task;

public interface TaskDao {

    Task create(Task task);

    List<Task> findAll();

    Task findById(int id);

    Task update(Task task);

    void delete(int id);

}

package ru.surpavel.bugtrackingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surpavel.bugtrackingsystem.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByProjectId(Long projectId);
}

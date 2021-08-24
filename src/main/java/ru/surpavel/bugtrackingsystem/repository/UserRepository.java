package ru.surpavel.bugtrackingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surpavel.bugtrackingsystem.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByProjectId(Long userId);

    List<User> findByTaskId(Long taskId);
}

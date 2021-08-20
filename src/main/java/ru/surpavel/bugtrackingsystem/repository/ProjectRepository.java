package ru.surpavel.bugtrackingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surpavel.bugtrackingsystem.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByTitle(String projectTitle);


}

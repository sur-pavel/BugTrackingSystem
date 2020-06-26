package ru.surpavel.bugtrackingsystem.dao;

import java.util.List;

import ru.surpavel.bugtrackingsystem.domain.User;

public interface UserDao {

    User create(User user);

    List<User> findAll();

    User findById(int id);

    User update(User user);

    void delete(int id);

}

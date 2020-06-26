package ru.surpavel.bugtrackingsystem.dao.sqlite;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.surpavel.bugtrackingsystem.dao.UserDao;
import ru.surpavel.bugtrackingsystem.dao.DaoException;
import ru.surpavel.bugtrackingsystem.domain.User;

public class UserDaoSQLite implements UserDao{
    private static final Logger log = LogManager.getLogger(UserDaoSQLite.class.getName());

    @Override
    public User create(User user) {
        log.debug("Creating " + user);
        try {
            
            log.info(user + " was created");
        } catch (RuntimeException e) {
            log.error("Can't create " + user, e);
            throw new DaoException("Can't create " + user, e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User update(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        
    }

}

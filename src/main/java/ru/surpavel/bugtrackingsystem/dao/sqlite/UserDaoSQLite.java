package ru.surpavel.bugtrackingsystem.dao.sqlite;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.surpavel.bugtrackingsystem.dao.UserDao;
import ru.surpavel.bugtrackingsystem.dao.DaoException;
import ru.surpavel.bugtrackingsystem.domain.User;

public class UserDaoSQLite implements UserDao{
    public Connection conn;
    public Statement stmt;
    public ResultSet resSet;
    private static final Logger log = LogManager.getLogger(UserDaoSQLite.class.getName());

    @Override
    public User create(User user) {
        log.debug("Creating " + user);
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:bugtrackingsystem.sqlite");
            stmt = conn.createStatement();
            String sql = new StringBuilder()
                    .append("INSERT INTO users (first_name, last_name, task_id) VALUES ('")
                    .append(user.getFirstName())                    
                    .append("', '" + user.getLastName())                    
                    .append("', '" + user.getTaskID() + "');")
                    .toString();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
            conn.close();
            log.info(user + " was created");
        } catch (SQLException e) {
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

package ru.surpavel.bugtrackingsystem.dao.sqlite;

import java.util.ArrayList;
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

public class UserDaoSQLite implements UserDao {
    public Connection conn;
    public Statement stmt;
    public ResultSet resSet;
    private String dbName = "BGS";
    private static final Logger log = LogManager.getLogger(UserDaoSQLite.class.getName());

    public void Conn() {
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".s3db");

            log.info("Database connected successfully.");
        } catch (ClassNotFoundException e) {
            log.error("Can't found class org.sqlite.JDBC", e);
        } catch (SQLException e) {
            log.error("Can't connect to database" + dbName, e);
            throw new DaoException("Can't connect to database" + dbName, e);
        }
    }

    @Override
    public User create(User user) {
        log.debug("Creating " + user);
        try {            
            stmt = conn.createStatement();
            String sql = "INSERT INTO 'users' ('first_name', 'last_name', 'task_id')" + " VALUES ('"
                    + user.getFirstName() + "', '" + user.getLastName() + "', " + user.getTaskID() + ");";
            stmt.execute(sql);
            log.info(user + " was created");
        } catch (SQLException e) {
            log.error("Can't create " + user, e);
            throw new DaoException("Can't create " + user, e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        try {
            resSet = stmt.executeQuery("SELECT * FROM users");

            while (resSet.next()) {
                users.add(new User(resSet.getInt("id"), resSet.getString("first_name"), resSet.getString("last_name")));
            }
            log.debug(String.format("Found %d users.", users.size()));
        } catch (SQLException e) {
            log.error("Can't find all users ", e);
            throw new DaoException("Can't find all users ", e);
        }
        return users;
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

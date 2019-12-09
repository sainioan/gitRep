/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class DBUserDao implements UserDao {

    private List<User> users;
    private String database;
    private MyBudgetDatabase db;
    private String password;

    String selectStmt = "SELECT * FROM user";

    public DBUserDao(MyBudgetDatabase db) {
        this.db = db;

        users = new ArrayList<>();

        db.initializeDatabase();
        Statement stmt = null;
        try {
            Connection conn = db.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectStmt);
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public DBUserDao(String database) throws SQLException {
        Connection conn = db.connect();
        System.out.println("testing " + conn);
        users = new ArrayList<>();
        this.database = database;
        db = new MyBudgetDatabase(database);
        db.initializeDatabase();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectStmt);
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The medhod saves a new user given into the database
     *
     * @param user new user object given as a parameter
     * @return returns true if the saving of the user into the database succeeds
     * @throws SQLException if saveUser fails
     */
    public boolean saveUser(User user) throws SQLException, Exception {
        Connection connection = db.connect();
        System.out.println("test " + connection);
        try {

            PreparedStatement saveDetailsStatement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO user (username, password) VALUES (?, ?);"
            );

            saveDetailsStatement.setString(1, user.getUsername());
            saveDetailsStatement.setString(2, user.getPassword());
            saveDetailsStatement.executeUpdate();
            saveDetailsStatement.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public void delete(String username) throws SQLException {
        Connection conn = db.connect();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE username = (?)");

        stmt.setString(1, username);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    /**
     * The medhod creates a new user given as a parameter by calling the
     * saveuser method
     *
     * @param user user object
     * @return returns the user object
     * @throws SQLException if saveUser fails
     */
    @Override
    public User create(User user) throws SQLException {

        users.add(user);

        try {
            saveUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        try {
            for (User u : this.users) {
                if (u.getUsername().equals(username)) {
                    return u;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public List<User> getAll() throws SQLException {
        Connection con = db.connect();
        PreparedStatement stmt = con.prepareStatement(selectStmt);
        users = new ArrayList<>();

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getString("username"), rs.getString("password"));
            user.setUsername(rs.getString("userBudget"));
            user.setPassword(rs.getString("password"));
            users.add(user);
        }
        stmt.close();
        rs.close();
        con.close();
        return users;
    }

    /**
     * Finds a user with the given username from the database
     *
     * @param username name of the logged in user
     * @return returns the user object with the username given as a parameter
     * @throws SQLException if unable to retrieve the username from the database
     */
    @Override
    public User findOne(String username) throws SQLException {
        Connection conn = db.connect();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        boolean findOne = rs.next();

        if (!findOne) {
            return null;
        }

        User user = new User(rs.getString("username"), rs.getString("password"));

        stmt.close();
        rs.close();
        conn.close();

        return user;
    }
}

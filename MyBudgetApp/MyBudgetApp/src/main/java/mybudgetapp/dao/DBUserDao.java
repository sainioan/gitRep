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
    private MyBudgetDatabase db;
    private String password;

    String selectStmt = "SELECT * FROM user";

    /**
     * Constructor with one parameter
     *
     * @param db the given database
     */
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

    /**
     * The method saves a new user given into the database
     *
     * @param user new user object given as a parameter
     * @return returns true if the saving of the user into the database succeeds
     * @throws SQLException if saveUser fails
     */
    public boolean saveUser(User user) throws SQLException, Exception {
        Connection connection = db.connect();
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
    /**
     * deletes the user given as a parameter. 
     * Method is used when deleting a user account.
     * @param user given as a parameter is the logged in user
     * @return true when executed successfully
     * @throws SQLException if data retrieval from the database fails
     */

    public boolean delete(User user) throws SQLException {
        String sql = "DELETE FROM user WHERE username = ?";
        Connection conn = db.connect();
        // try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getUsername());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        return true;
    }

    /**
     * The method creates a new user given as a parameter by calling the
     * saveUser method
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
   /**
     * The method looks for a specific user by username given as a parameter 
     * @param username given as a parameter
     * @return returns the user object
     */
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
            conn.close();
            return null;

        } else {

            User user = new User(rs.getString("username"), rs.getString("password"));

            stmt.close();
            rs.close();
            conn.close();
            return user;

        }
    }
}

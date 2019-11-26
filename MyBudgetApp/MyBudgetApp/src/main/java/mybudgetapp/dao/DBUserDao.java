/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class DBUserDao implements UserDao {

    private List<User> users;
    private String database;
    private  MyBudgetDatabase db;
    private String password;

    String selectStmt = "SELECT * FROM user";
public DBUserDao (MyBudgetDatabase db){
    this.db = db;
}
    public DBUserDao(String database) throws SQLException {
        Connection conn = db.connect();
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
    public List<User> findAll() throws SQLException {
        Connection con = db.connect();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM UserAccount");
        List<User> users = new ArrayList<>();

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
    

    public void save() throws Exception {
        Connection connection = db.connect();

        try {
            for (User user : users) {
                PreparedStatement saveDetailsStatement = connection.prepareStatement(
                        "INSERT INTO user (username, password) VALUES (?, ?);"
                );

                saveDetailsStatement.setString(1, user.getUsername());
                saveDetailsStatement.setString(2, user.getPassword());

                saveDetailsStatement.executeUpdate();
                saveDetailsStatement.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public User saveOrUpdate(User user) throws SQLException {
        Connection conn = db.connect();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (username, password) VALUES (?,?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
        return user;
    }
  
    

    public void delete(String username) throws SQLException {
        Connection conn = db.connect();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE username = (?)");

        stmt.setString(1, username);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public User create(User user) throws SQLException {
        
        users.add(user);
        try{
        save();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername()
                .equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User findOne(String username) throws SQLException {
        Connection conn = db.connect();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
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

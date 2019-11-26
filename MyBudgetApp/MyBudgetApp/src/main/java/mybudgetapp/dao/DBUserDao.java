/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    String selectStmt = "SELECT * FROM user";
    public DBUserDao(String database) throws SQLException{
        Connection conn = db.connect();
        users = new ArrayList<>();
        this.database = database;
        db = new MyBudgetDatabase(database);
        db.initializeDatabase();
        Statement stmt = null;
        try{
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectStmt); 
        while (rs.next()){
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        users.add(user);
        }   
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public User create(User user) throws Exception {
        return user;
    }

    @Override
    public User findByUsername(String username) {
        String password = "";
        User theUser = new User(username, password);
        return theUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        return users;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mybudgetapp.domain.Balance;
import mybudgetapp.domain.Category;
import mybudgetapp.domain.MyBudget;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class DBBudgetDao implements BudgetDao {
    
    private int id;
    private String description;
    private boolean done;
    private MyBudgetDatabase db;
    private double amount;
    private String database;
    private List<Category> categories;
    String selectStmt = "SELECT*FROM category";

    //private User user;
    public DBBudgetDao(MyBudgetDatabase db) {
        this.db = db;
        categories = new ArrayList<>();
        db.initializeDatabase();
        Statement stmt = null;
        try {
            Connection conn = db.connect();
            db = new MyBudgetDatabase(database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectStmt);
            while (rs.next()) {
                Category category = new Category();
                category.setUserName(rs.getString("categoryUser"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public DBBudgetDao(String database) throws SQLException {
        Connection conn = db.connect();
        
        categories = new ArrayList<>();
        this.database = database;
        db = new MyBudgetDatabase(database);
        db.initializeDatabase();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectStmt);
            while (rs.next()) {
                Category category = new Category();
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void saveCategory(Category category) throws Exception {
        Connection connection = db.connect();
        
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO category (categoryUser, name) VALUES (?,?);"
            );
            statement.setString(1, category.getUserName());
            statement.setString(2, category.getName());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//    public void saveBalance(Balance balance) throws Exception {
//        Connection connection = db.connect();
//        System.out.println("Balance test" + connection);
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSER OR REPLACE INTO Balance (name) VALUES (?);"
//            );
//            statement.setFloat(1, balance.getAmount());
//            statement.executeUpdate();
//            statement.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public List<Category> getAllCategories() throws SQLException {
        Connection con = db.connect();
        String sql = "SELECT*FROM category";
//        PreparedStatement stmt = con.prepareStatement("sql");
//        categories = new ArrayList<>();

//        ResultSet rs = stmt.executeQuery();
//        while (rs.next()) {
//            Category category = new Category(rs.getString("name"));
//            user.setName(rs.getString("name"));
//            categories.add(category);
//        }
//        stmt.close();
//        rs.close();
//        con.close();
//        return users;
//    }
        return categories;
    }
    
    @Override
    
    public List<MyBudget> getAll() throws SQLException {
        return new ArrayList<MyBudget>();

//         @Override
//    public List<User> getAll()  {
//        Connection con = db.connect();
//        PreparedStatement stmt = con.prepareStatement(selectStmt);
//        users = new ArrayList<>();
//
//        ResultSet rs = stmt.executeQuery();
//        while (rs.next()) {
//            User user = new User(rs.getString("username"), rs.getString("password"));
//            user.setUsername(rs.getString("userBudget"));
//            user.setPassword(rs.getString("password"));
//            users.add(user);
//        }
//        stmt.close();
//        rs.close();
//        con.close();
//        return users;
//    }
    }
    
    @Override
    public MyBudget createBudget() throws SQLException {
        
        return new MyBudget(description, amount);
    }
    
    public Category create(Category category) throws SQLException {
        
        categories.add(category);
        
        try {
            saveCategory(category);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return category;
    }
    
}

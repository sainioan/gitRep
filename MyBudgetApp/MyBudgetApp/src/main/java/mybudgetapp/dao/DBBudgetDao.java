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
import mybudgetapp.domain.Expense;
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
    private List<Expense> expenses = new ArrayList<>();

    public DBBudgetDao(MyBudgetDatabase db) {
        this.db = db;
        categories = new ArrayList<>();
        db.initializeDatabase();
        Statement stmt = null;
        try {
            Connection conn = db.connect();
            db = new MyBudgetDatabase(database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select*FROM category");
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
            ResultSet rs = stmt.executeQuery("Select*From category where categoryUser = ?");
            ResultSet rs2 = stmt.executeQuery("Select*From expense where user_username =?");
            while (rs.next()) {
                Category category = new Category();
                category.setUserName(rs.getString("categoryUser"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            while (rs2.next()) {
                Expense expense = new Expense();
                expense.setUserName(rs2.getString("user_username"));
                expense.setCategoryName(rs2.getString("category_name"));
                expense.setAmount(rs2.getDouble("amount"));
                expense.setDate(rs2.getDate("time").toLocalDate());
                expenses.add(expense);
            }
        } catch (Exception e) {
            System.out.println("dbbudgetdaoconstructor " + e.getMessage());
        }
    }

    public void saveCategory(Category category) throws SQLException, Exception {
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

    public void saveExpense(Expense expense) throws SQLException, Exception {

        Connection connection = db.connect();
        Float amountF = (float) expense.getAmount();
        //Date sqlDate = Date.valueOf(expense.getDate());
        java.sql.Date sqlDate = Date.valueOf(expense.getDate());
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO expense (user_username, category_name, amount, time) VALUES (?,?,?,?);"
            );
            statement.setString(1, expense.getUserName());
            statement.setString(2, expense.getCategoryName());
            statement.setFloat(3, amountF);
            statement.setDate(4, sqlDate);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("saveExpanse message is... " + e.getMessage());
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

    public List<Category> getAllCategories(User user) throws SQLException {
        try {
            Connection con = db.connect();
            String sql = "SELECT*FROM category where categoryUser = ?";
            String categoryUser = user.getUsername();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, categoryUser);
            ResultSet rs = stmt.executeQuery();
            categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category(rs.getString("categoryUser").trim(), rs.getString("name"));
                category.setId(rs.getInt("id"));
                categories.add(category);

                stmt.close();
                rs.close();
                con.close();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }

        System.out.println(categories.toString());
        return categories;
    }

    @Override
    public List<MyBudget> getAll() throws SQLException {
        return new ArrayList<MyBudget>();

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

    public Expense create(Expense expense) throws SQLException {

        try {
            expenses.add(expense);
            saveExpense(expense);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return expense;
    }

}

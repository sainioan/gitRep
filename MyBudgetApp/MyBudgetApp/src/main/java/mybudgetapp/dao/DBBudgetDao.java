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
import mybudgetapp.domain.Income;
import mybudgetapp.domain.MyBudget;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class DBBudgetDao implements BudgetDao {

    private int id;
    private String description;
    private MyBudgetDatabase db;
    private String database;
    private List<Category> categories = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();
    private List<Income> incomeList = new ArrayList<>();
    private String sql2 = "SELECT*FROM income where user_username = ?";
    private String sql3 = "SELECT*FROM expense where user_username = ?";
    private String sql4 = "SELECT*FROM category where categoryUser = ?";
    private List<Income> incomeByUser = new ArrayList<>();
    private List<Expense> expensesByUser = new ArrayList<>();

    public DBBudgetDao(MyBudgetDatabase db) {
        this.db = db;
        categories = new ArrayList<>();
        db.initializeDatabase();
        Statement stmt = null;
//        try {
//            Connection conn = db.connect();
//            db = new MyBudgetDatabase(database);
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("Select*FROM category where categoryUser = ?");
//            while (rs.next()) {
//                Category category = new Category();
//                category.setUserName(rs.getString("categoryUser"));
//                category.setName(rs.getString("name"));
//                categories.add(category);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

    }

    public DBBudgetDao(String database) throws SQLException {
        Connection conn = db.connect();

        categories = new ArrayList<>();
        this.database = database;
        db = new MyBudgetDatabase(database);
        db.initializeDatabase();

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
        Date sqlDate = Date.valueOf(expense.getDate());
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
            System.out.println("saveExpense message is... " + e.getMessage());
        }
    }

    public void saveIncome(Income income) throws SQLException, Exception {
        Connection connection = db.connect();
        Float amountF2 = (float) income.getAmount();
        Date sqlDate = Date.valueOf(income.getDate());
        try {
            PreparedStatement statementIn = connection.prepareStatement(
                    "INSERT OR REPLACE INTO income (user_username, amount, time) VALUES (?,?,?);"
            );
            statementIn.setString(1, income.getUserName());
            statementIn.setFloat(2, amountF2);
            statementIn.setDate(3, sqlDate);
            statementIn.executeUpdate();
            statementIn.close();
        } catch (SQLException e) {
            System.out.println("saveIncome error message is... " + e.getMessage());
        }
    }
//    public void saveBalance(Balance balance) throws Exception {
//        Connection connection = db.connect();
//        System.out.println("Balance test" + connection);
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSERT OR REPLACE INTO Balance (user_username, amount, time) VALUES (?, ?, ?);"
//            );
    // statement.setString(1, balance.get   
//            statement.setFloat(2, balance.getAmount());
//            statement.executeUpdate();
//            statement.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public List<Expense> getAllExpenses(User user) throws SQLException {
        try {
            Connection con = db.connect();
            String userUsername = user.getUsername();
            PreparedStatement stmt = con.prepareStatement(sql3);
            stmt.setString(1, userUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense(rs.getString("user_username").trim(), rs.getString("category_name"), rs.getFloat("amount"), rs.getDate("time").toLocalDate());
                expense.setId(rs.getInt("id"));
                expensesByUser.add(expense);
                stmt.close();
                rs.close();
                con.close();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return expensesByUser;
    }

    public List<Income> getAllIncome(User user) throws SQLException {

        try {
            Connection con = db.connect();
            String userUsername = user.getUsername();
            PreparedStatement stmt = con.prepareStatement(sql2);
            stmt.setString(1, userUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Income income = new Income(rs.getString("user_username").trim(), rs.getFloat("amount"), rs.getDate("time").toLocalDate());
                income.setId(rs.getInt("id"));
                incomeByUser.add(income);
                incomeList.add(income);
                stmt.close();
                rs.close();
                con.close();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return incomeByUser;

    }

    public List<Category> getAllCategories(User user) throws SQLException {
        try {
            Connection con = db.connect();
            String categoryUser = user.getUsername();
            PreparedStatement stmt = con.prepareStatement(sql4);
            stmt.setString(1, categoryUser);
            ResultSet rs = stmt.executeQuery();
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
        return categories;
    }

    @Override
    public List<MyBudget> getAll() throws SQLException {
        return new ArrayList<MyBudget>();

    }

    @Override
    public MyBudget createBudget() throws SQLException {
        Double amount = 0.0;
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

    public Income create(Income income) throws SQLException {

        try {
            incomeList.add(income);
            saveIncome(income);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(incomeList.toString());
        return income;
    }

    public Expense create(Expense expense) throws SQLException {

        try {
            expenses.add(expense);
            saveExpense(expense);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(expenses.toString());
        return expense;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.sql.Connection;
import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import mybudgetapp.dao.BudgetDao;
import mybudgetapp.dao.DBBudgetDao;
import mybudgetapp.dao.DBUserDao;
import mybudgetapp.dao.MyBudgetDatabase;
import mybudgetapp.dao.UserDao;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class MyBudgetService {

    private MyBudgetDatabase mybDatabase;
    private DBBudgetDao dbbudgetDao;
    private DBUserDao dbuserDao;
    private User loggedIn;
    private String username;
    private String password;
    private LocalDate date;
    private Balance currentBalance;
    private ObservableList<ObservableList> data;
    private TableView tableview;

    /**
     *
     * @param db the database given as a parameter
     * @throws SQLException when connection to the database fails
     */
    public MyBudgetService(MyBudgetDatabase db) throws SQLException {
        this.mybDatabase = db;
        this.mybDatabase.initializeDatabase();
        dbuserDao = new DBUserDao(mybDatabase);
        dbbudgetDao = new DBBudgetDao(mybDatabase);
        this.date = LocalDate.now();

    }

    public MyBudgetService(MyBudgetDatabase db, String username) throws SQLException {
        this.username = username;
        this.mybDatabase = db;
        this.mybDatabase.initializeDatabase();
        dbuserDao = new DBUserDao(mybDatabase);
        dbbudgetDao = new DBBudgetDao(mybDatabase);
        this.date = LocalDate.now();
    }

    public MyBudgetService() {

        this.mybDatabase.initializeDatabase();
        dbuserDao = new DBUserDao(mybDatabase);

    }

    /**
     * method creates a new expense category
     *
     * @param username the name of the logged in user
     * @param description the category name inputted by the user
     * @return true when the creation of a new category succeeds
     */
    public boolean createCategory(String username, String description) {
        Category category = new Category(username, description);
        try {
            dbbudgetDao.create(category);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * the method creates a new expense entry
     *
     * @param username the username of the logged in user
     * @param category the category inputted by the user
     * @param amount the expense amount as double inputted by the user
     * @param date the date of the expense entry
     * @return returns true if the entry succeeds
     * @throws SQLException if the entry fails
     */
    public boolean createExpense(String username, String category, double amount, LocalDate date) throws SQLException {
        Expense expense = new Expense(username, category, amount, date);
        try {
            if (!category.equals("create new") && (!username.equals("") && (date != null) && (amount > 0))) {
                dbbudgetDao.create(expense);
                return true;

            } else if ((amount < 0) || (username.equals("")) || (date == null)) {
                return false;
            } else {
                Category newCategory = new Category(username, category);
                dbbudgetDao.create(newCategory);
                dbbudgetDao.create(expense);
            }
        } catch (SQLException ex) {
            System.out.println("createExpense error message is..." + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean createIncome(String username, double amount, LocalDate date) throws SQLException, Exception {
        Income income = new Income(username, amount, date);

        try {
            dbbudgetDao.create(income);
        } catch (SQLException ex) {
            System.out.println("createIncome error message is..." + ex.getMessage());
            return false;
        }
        return true;
    }

    public String getMostRecent(User user) throws SQLException {
        user = loggedIn;
        List<Balance> list = dbbudgetDao.getBalanceList(loggedIn);
        System.out.println(list.toString());
        Balance mostR = list.get(list.size() - 1);
        if (mostR == null) {
            return "";
        }
        return mostR.toString();
    }

    public boolean updateBalanceNewIncome(String username, double income, LocalDate date) throws SQLException, Exception {

        try {
            currentBalance = dbbudgetDao.findOne(username);
            if (currentBalance == null) {
                Balance balance = new Balance(username, income, date);
                dbbudgetDao.saveBalance(balance);
            } else {
                currentBalance.addIncome(income);
                double newBalance = currentBalance.getBalance();
                currentBalance.setBalance(newBalance);
                currentBalance.setDate(date);
                dbbudgetDao.saveBalance(currentBalance);
            }
        } catch (SQLException ex) {
            System.out.println("updateBalance error message is..." + ex.getMessage());
            return false;
        }
        return true;
    }

    public String updateBalanceLabel() throws SQLException, Exception {
        if (loggedIn != null) {
            if (dbbudgetDao.findOne(loggedIn.getUsername()) != null) {
                currentBalance = dbbudgetDao.findOne(loggedIn.getUsername());

                System.out.println(currentBalance.toString());
            } else {
                currentBalance = new Balance(loggedIn.getUsername(), 0.0, LocalDate.now());
            }

            return currentBalance.toString();

        }
        return "";

    }

    public boolean updateBalanceNewExpense(String username, double expense, LocalDate date) throws SQLException, Exception {

        currentBalance = dbbudgetDao.findOne(username);
        if (currentBalance == null) {
            return false;
        }
        try {
            currentBalance.deductExpense(expense);
            double newBalance = currentBalance.getBalance();
            currentBalance.setBalance(newBalance);
            dbbudgetDao.saveBalance(currentBalance);
            return true;

        } catch (SQLException ex) {
            System.out.println("updateBalance error message is..." + ex.getMessage());
            Logger.getLogger(MyBudgetService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean login(String username, String password) {
        User user = dbuserDao.findByUsername(username);
        if (user == null) {
            return false;
        }

        loggedIn = user;
        return true;
    }

    public User getLoggedUser() {
        System.out.println(loggedIn);
        return loggedIn;
    }

    public void logout() {
        loggedIn = null;
    }

    public boolean createUser(String username, String password) {
        User user = new User(username, password);
        if (!user.validateUsername().isEmpty()) {
            return false;
        }
        if (!user.validatePassword().isEmpty()) {
            return false;
        }

        if (dbuserDao.findByUsername(username) != null) {
            return false;
        }

        try {
            dbuserDao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;

    }

    public ObservableList<Balance> createBalanceList(User user) throws SQLException {
        ObservableList<Balance> items2 = FXCollections.observableArrayList();
        List<Balance> balances = dbbudgetDao.getBalanceList(loggedIn);

        for (Balance b : balances) {
            items2.add(b);
        }

        System.out.println(items2);
        return items2;

    }

    public ObservableList<String> createChoices(User user) throws SQLException {
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Category> categories = dbbudgetDao.getAllCategories(loggedIn);
        ArrayList<String> categorynames = new ArrayList<>();

        for (Category c : categories) {
            String name = c.getName();
            categorynames.add(name);
        }
        for (String s : categorynames) {
            items.add(s.trim());
        }
        System.out.println(categories);
        System.out.println(items);
        return items;

    }

    public Boolean validateUsernameInput(String username) {
        return ((username != null) && username.matches("[A-Za-z0-9_]+") && username.length() >= 5);
    }

    public boolean deleteCategory(User user) {
        try {
            dbbudgetDao.deleteCategory(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean deleteExpense(User user) {
        try {
            dbbudgetDao.deleteExpense(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean deleteIncome(User user) {
        try {
            dbbudgetDao.deleteIncome(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean deleteBalance(User user) {
        try {
            dbbudgetDao.deleteBalance(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean deleteUser(User user) {
        if (loggedIn == null) {
            return false;
        } else {
            try {
                dbuserDao.delete(user);
                dbbudgetDao.deleteCategory(user);
                dbbudgetDao.deleteExpense(user);
                dbbudgetDao.deleteIncome(user);
                dbbudgetDao.deleteBalance(user);

            } catch (Exception ex) {
                return false;
            }
            return true;
        }

    }
}

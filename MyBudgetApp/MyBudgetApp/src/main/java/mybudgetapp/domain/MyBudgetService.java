/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
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
    private DBBudgetDao DBbudgetDao;
    private DBUserDao dbuserDao;
    // private UserDao userDao;
    private User loggedIn;
    private String username;
    private String password;
    private LocalDate date;

    public MyBudgetService(MyBudgetDatabase db) throws SQLException {
        this.mybDatabase = db;
        this.mybDatabase.initializeDatabase();
        dbuserDao = new DBUserDao(mybDatabase);
        this.date = LocalDate.now();
////       
//        this.food = new Food(-1, "default");
//        this.date = LocalDate.now();
    }

    public MyBudgetService() {

        this.mybDatabase.initializeDatabase();
        dbuserDao = new DBUserDao(mybDatabase);

    }

    public boolean createCategory(String description) {
        Category category = new Category(description);
        try {
            DBbudgetDao.create(category);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

//content = some description of the budget
    public boolean createBudget(String content, double amount) {
        MyBudget mb = new MyBudget(content, amount);
        try {
            DBbudgetDao.createBudget();
        } catch (Exception ex) {
            return false;
        }
        return true;
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

    public Boolean checkUsername(String username) {
        this.username = username;
        try {
            if (validateUsernameInput(username) && dbuserDao.findOne(username.trim()) != null) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(MyBudgetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean validateUsernameInput(String username) {
        return ((username != null) && username.matches("[A-Za-z0-9_]+") && username.length() >= 5);
    }

}

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
    private BudgetDao budgetDao;
    private DBUserDao dbuserDao;
    private UserDao userDao;
    private User loggedIn;
    private String username;
    private String password;

    public MyBudgetService() throws SQLException {
        this.mybDatabase = new MyBudgetDatabase("mybudgetdatabase.db");
    }

    public MyBudgetService(BudgetDao bd, UserDao ud) {
        this.budgetDao = bd;
        this.userDao = ud;
    }

    public boolean createBudget(String content) {
        MyBudget mb = new MyBudget(content, loggedIn);
        try {
            budgetDao.create(mb);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    // budgetDao.
    //public boolean createUser()
    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
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
        if (dbuserDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, password);
        try {
            userDao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;

    } public Boolean checkUsername(String username) {
        this.username = username;
        try {
            if (validateUsernameInput(username) && dbuserDao.findOne(username.trim()) != null) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(MyBudgetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } public Boolean createUser(String newUsername) {
        try {
            if (validateUsernameInput(newUsername) && dbuserDao.findOne(newUsername) == null) {
                dbuserDao.saveOrUpdate(new User(newUsername, password));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyBudgetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } public Boolean validateUsernameInput(String username) {
        return ((username != null) && username.matches("[A-Za-z0-9_]+") && username.length() >= 5);
    }
    
}

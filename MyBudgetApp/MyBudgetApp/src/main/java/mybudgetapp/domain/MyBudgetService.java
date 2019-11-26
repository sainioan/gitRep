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
import mybudgetapp.dao.BudgetDao;
import mybudgetapp.dao.MyBudgetDatabase;
import mybudgetapp.dao.UserDao;

/**
 *
 * @author anniinasainio
 */
public class MyBudgetService {

    private MyBudgetDatabase mybDatabase;
    private BudgetDao budgetDao;
    private UserDao userDao;
    private User loggedIn;

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

    public boolean createUser(String username, String name) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        try {
            userDao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;

    }
}

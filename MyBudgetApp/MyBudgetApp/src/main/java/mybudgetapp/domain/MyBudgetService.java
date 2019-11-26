/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

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
    // public boolean createBudget()
    // budgetDao.

    //public boolean createUser()
    public boolean login(String username) {
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

}

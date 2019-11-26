/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import mybudgetapp.dao.BudgetDao;
import mybudgetapp.dao.UserDao;

/**
 *
 * @author anniinasainio
 */
public class MyBudgetService {

    BudgetDao budgetDao;
    private UserDao userDao;
    private User loggedIn;

    public MyBudgetService(BudgetDao bd) {
        this.budgetDao = bd;
    }

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

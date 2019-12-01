/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    //private User user;
    public DBBudgetDao(MyBudgetDatabase db) {

    }

    public DBBudgetDao() {

    }

    public void save(User user) throws Exception {
        Connection connection = db.connect();

        try {

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO user (username, password) VALUES (?, ?);"
            );

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void saveCategory(Category category) throws Exception {
        Connection connection = db.connect();
        System.out.println("category test" + connection);
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSER OR REPLACE INTO category (name) VALUES (?);"
            );
            statement.setString(1, category.getName());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveBalance(Balance balance) throws Exception {
        Connection connection = db.connect();
        System.out.println("Balance test" + connection);
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSER OR REPLACE INTO Balance (name) VALUES (?);"
            );
            statement.setString(1, balance.getName());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    {
    }

    @Override

    public List<MyBudget> getAll() {
        return new ArrayList<MyBudget>();
    }

    @Override
    public MyBudget create() throws Exception {

        return new MyBudget(description, amount);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import mybudgetapp.domain.Category;
import mybudgetapp.domain.Expense;
import mybudgetapp.domain.Expense;
import mybudgetapp.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Object;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.io.*;

/**
 *
 * @author anniinasainio
 */
public class MyBudgetDatabase {

    private final String dbName;

    public MyBudgetDatabase(String databaseName) throws SQLException {
        this.dbName = databaseName;
        
    }

    public MyBudgetDatabase() throws SQLException {
        this.dbName = "mybudgetapp.db";
     }

    public Connection connect() throws SQLException {
        Connection connection = null;
        try {

            String url = "jdbc:sqlite:" + dbName;
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return connection;
    }

    public void disconnect(Connection connection, PreparedStatement stmt, ResultSet rs) throws SQLException {
        rs.close();
        stmt.close();
        connection.close();
    }

    public void disconnect(Connection conn, PreparedStatement stmt) throws SQLException {
        stmt.close();
        conn.close();
    }

    public void initializeDatabase() {
        // The following methods will create required tables if they do not already exist in the database

        initializeCategory();
        initializeExpense();
        initializeIncome();
        initializeCurrentBalance();
        initializeUser();

    }

    public void initializeCategory() {
        try {
            Connection connection = connect();

            PreparedStatement createCategoryTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS category ("
                    + "id INTEGER NOT NULL PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "allocated float);"
            );
            createCategoryTable.execute();
            createCategoryTable.close();

            connection.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void initializeUser() {
        try {
            Connection connection = connect();

            PreparedStatement createCategoryTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user ("
                    + "id INTEGER NOT NULL PRIMARY KEY, "
                    + "username VARCHAR(100)"
                    + "password VARCHAR(100));"
            );
            createCategoryTable.execute();
            createCategoryTable.close();

            connection.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void initializeExpense() {
        try {
            Connection connection = connect();

            PreparedStatement createExpenseTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS expense ("
                    + "id INTEGER NOT NULL PRIMARY KEY, "
                    + "category_id INTEGER, "
                    + "amount float, "
                    + "time DATE,"
                    + "FOREIGN KEY (category_id) REFERENCES category(id));"
            );
            createExpenseTable.execute();
            createExpenseTable.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void initializeIncome() {
        try {
            Connection connection = connect();

            PreparedStatement createIncomeTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS income ("
                    + "id INTEGER PRIMARY KEY, "
                    + "amount float, "
                    + "time DATE, "
                    + ");"
            );
            createIncomeTable.execute();
            createIncomeTable.close();

            connection.close();
        } catch (SQLException e) {
            /* Do nothing */
        }

    }

    public void initializeCurrentBalance() {
        try {
            Connection connection = connect();

            PreparedStatement createBalanceTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS balance ("
                    + "id INTEGER NOT NULL PRIMARY KEY, "
                    //    + "expense_id integer, "
                    + "amount float, "
                    + "time DATE, "
                    // + "FOREIGN KEY (category_id) REFERENCES category(id),"
                    //  + "FOREIGN KEY (expense_id) REFERENCES expense(id)"
                    + ");"
            );
            createBalanceTable.execute();
            createBalanceTable.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public int getLastId(Connection connection) {
        int id = -1;
        try {
            PreparedStatement getLastId = connection.prepareStatement("SELECT last_insert_rowid() AS id;");
            ResultSet resultSet = getLastId.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            return id;
        } catch (SQLException ex) {
            Logger.getLogger(MyBudgetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void saveExpense(Connection connection, int categoryid, double amount, LocalDate time) {
        float fAmount = (float) amount;
        try {
            PreparedStatement saveDetailsStatement = connection.prepareStatement(
                    "INSERT INTO expense (category_id, amount, time) VALUES (?, ?, ?);"
            );

            saveDetailsStatement.setInt(1, categoryid);
            saveDetailsStatement.setFloat(2, fAmount);
            saveDetailsStatement.setDate(3, Date.valueOf(time));

            saveDetailsStatement.executeUpdate();
            saveDetailsStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MyBudgetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveIncome(Connection connection, double amount, LocalDate time) {
        float fAmount = (float) amount;
        try {
            PreparedStatement saveDetailsStatement = connection.prepareStatement(
                    "INSERT INTO income (amount, time) VALUES (?, ?);"
            );

            saveDetailsStatement.setFloat(1, fAmount);
            saveDetailsStatement.setDate(2, Date.valueOf(time));

            saveDetailsStatement.executeUpdate();
            saveDetailsStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MyBudgetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void saveUser(Connection connection, String username, String password) {
      
        try {
            PreparedStatement saveDetailsStatement = connection.prepareStatement(
                    "INSERT INTO user (username, password) VALUES ( ?, ?);"
            );
            saveDetailsStatement.setString(1, username);
            saveDetailsStatement.setString(2, password);

            saveDetailsStatement.executeUpdate();
            saveDetailsStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MyBudgetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

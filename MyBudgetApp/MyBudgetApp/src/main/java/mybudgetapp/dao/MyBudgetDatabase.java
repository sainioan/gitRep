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

    private String dbName;

    /**
     * constructor where user gives the name for the database when invoked
     * @param databaseName the address of database given as a parameter
     * @throws SQLException when connection fails
     */
    public MyBudgetDatabase(String databaseName) throws SQLException {
        this.dbName = databaseName;
    }

    public MyBudgetDatabase() throws SQLException {
        this.dbName = "myBudget.db";
    }

    public Connection connect() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
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

    /**
     * initializeDatabase method initializes the five database tables
     * @return returns true if the database tables are created
     */
    public boolean initializeDatabase() {
        // The following methods will create required tables if they do not already exist in the database
        try {
            initializeCategory();
            initializeExpense();
            initializeIncome();
            initializeBalance();
            initializeUser();
        } catch (Throwable t) {

            System.out.println(t.getMessage());
            return false;
        }
        return true;
    }

    public void initializeCategory() {
        try {
            Connection connection = connect();
            PreparedStatement createCategoryTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS category ("
                    + "id INTEGER PRIMARY KEY,"
                    + "categoryUser varchar, "
                    + "name VARCHAR(100),"
                    + "FOREIGN KEY (categoryUser) REFERENCES user(username));"
            );
            createCategoryTable.execute();
            createCategoryTable.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     */
    public void initializeUser() {
        try {
            Connection connection = connect();

            PreparedStatement createCategoryTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user ("
                    + "id INTEGER PRIMARY KEY, "
                    + "username VARCHAR(100),"
                    + "password VARCHAR(100),"
                    + "UNIQUE(username, password)"
                    + ");"
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
                    + "id INTEGER PRIMARY KEY, "
                    + "user_username varchar,"
                    + "category_name varchar, "
                    + "amount float, "
                    + "time DATE,"
                    + "FOREIGN KEY (user_username) REFERENCES user(username),"
                    + "FOREIGN KEY(category_name) REFERENCES category(name)"
                    + ");"
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
                    + "user_username VARCHAR(100),"
                    + "amount float, "
                    + "time DATE,"
                    + "FOREIGN KEY (user_username) REFERENCES user(username)"
                    + ");"
            );
            createIncomeTable.execute();
            createIncomeTable.close();

            connection.close();
        } catch (SQLException e) {
            /* Do nothing */
        }
    }

    public void initializeBalance() {
        try {
            Connection connection = connect();

            PreparedStatement createBalanceTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS balance ("
                    + "id INTEGER PRIMARY KEY,"
                    + "user_username VARCHAR(100),"
                    + "amount float, "
                    + "time DATE,"
                    + "FOREIGN KEY (user_username) REFERENCES User(username));"
            );
            createBalanceTable.execute();
            createBalanceTable.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    public int getLastId(Connection connection) {
//        int id = -1;
//        try {
//            PreparedStatement getLastId = connection.prepareStatement("SELECT last_insert_rowid() AS id;");
//            ResultSet resultSet = getLastId.executeQuery();
//
//            while (resultSet.next()) {
//                id = resultSet.getInt("id");
//            }
//            return id;
//        } catch (SQLException ex) {
//            Logger.getLogger(MyBudgetDatabase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
//    }

//    public void saveExpense(Connection connection, int categoryid, double amount, LocalDate time) {
//        float fAmount = (float) amount;
//        try {
//            PreparedStatement saveDetailsStatement = connection.prepareStatement(
//                    "INSERT INTO expense (user_username, category_name, amount, time) VALUES (?, ?, ?);"
//            );
//
//            saveDetailsStatement.setInt(1, categoryid);
//            saveDetailsStatement.setFloat(2, fAmount);
//            saveDetailsStatement.setDate(3, Date.valueOf(time));
//
//            saveDetailsStatement.executeUpdate();
//            saveDetailsStatement.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(MyBudgetDatabase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void saveIncome(Connection connection, double amount, LocalDate time) {
//        float fAmount = (float) amount;
//        try {
//            PreparedStatement saveDetailsStatement = connection.prepareStatement(
//                    "INSERT INTO income (amount, time) VALUES (?, ?);"
//            );
//
//            saveDetailsStatement.setFloat(1, fAmount);
//            saveDetailsStatement.setDate(2, Date.valueOf(time));
//            saveDetailsStatement.executeUpdate();
//            saveDetailsStatement.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(MyBudgetDatabase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void saveUser(Connection connection, String username, String password) {
//
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSERT INTO user (username, password) VALUES ( ?, ?);"
//            );
//            statement.setString(1, username);
//            statement.setString(2, password);
//            statement.executeUpdate();
//            statement.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(MyBudgetDatabase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}

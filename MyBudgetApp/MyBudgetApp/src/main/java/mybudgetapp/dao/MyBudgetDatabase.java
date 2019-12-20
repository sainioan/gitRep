/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anniinasainio
 */
public class MyBudgetDatabase {

    private String dbName;

    /**
     * constructor where user gives the name for the database when invoked
     *
     * @param databaseName the address of database given as a parameter
     * @throws SQLException when connection fails
     */
    public MyBudgetDatabase(String databaseName) throws SQLException {
        this.dbName = databaseName;
    }

    /**
     * constructor without parameters
     *
     * @throws SQLException when connection fails
     */
    public MyBudgetDatabase() throws SQLException {
        this.dbName = "myBudget.db";
    }

    /**
     * Connects to the database
     *
     * @return connection to the database
     * @throws java.sql.SQLException when the connection to the database fails
     */
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

    /**
     * initializeDatabase method initializes the five database tables
     *
     * @return returns true if the database tables are created
     */
    public boolean initializeDatabase() {
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

    /**
     * The method initializeCategory will create the table category if it does not already exist 
     * in the database
     */
    public void initializeCategory() {
        try {
            Connection connection = connect();
            PreparedStatement createCategoryTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS category ("
                    + "id INTEGER PRIMARY KEY,"
                    + "categoryUser varchar,"
                    + "name varchar(100),"
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
     * The method initializeUser will create the table user if it does not already exist 
     * in the database
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

     /**
     * The method initializeExpense will create the table expense if it does not already exist 
     * in the database
     */
    public void initializeExpense() {
        try {

            Connection connection = connect();

            PreparedStatement createExpenseTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS expense ("
                    + "id INTEGER PRIMARY KEY, "
                    + "user_username varchar,"
                    + "category_name varchar,"
                    + "amount float,"
                    + "time varchar,"
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

    /**
     * The method initializeIncome will create the table income if it does not already exist 
     * in the database
     */
    public void initializeIncome() {
        try {
            Connection connection = connect();

            PreparedStatement createIncomeTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS income ("
                    + "id INTEGER PRIMARY KEY, "
                    + "user_username VARCHAR(100),"
                    + "amount float, "
                    + "time varchar,"
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

   /**
     * The method initializeBalance will create the table Balance if it does not already exist 
     * in the database
     */
    public void initializeBalance() {
        try {
            Connection connection = connect();

            PreparedStatement createBalanceTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS balance ("
                    + "id INTEGER PRIMARY KEY,"
                    + "user_username varchar(100),"
                    + "amount float, "
                    + "time varchar,"
                    + "FOREIGN KEY (user_username) REFERENCES User(username));"
            );
            createBalanceTable.execute();
            createBalanceTable.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

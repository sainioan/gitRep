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

public class MyBudgetDatabase  {

    private final String dbName;

    public MyBudgetDatabase(String databaseName) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        this.dbName = databaseName;
    }

    public Connection connect() throws SQLException {
        Connection connection = null;
        try{
        }catch (Exception e){
            String url =  "jdbc:sqlite:" + dbName;
            connection =      DriverManager.getConnection(url);
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

    private void initializeTables() {
        // The following methods will create required tables if they do not already exist in the database

        initializeCategory();
        initializeExpense();
        initializeIncome();
        initializeCurrentBalance();
        
    }

    private void initializeCategory() {
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

    private void initializeExpense() {
        try {
            Connection connection = connect();

            PreparedStatement createExpenseTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS expense ("
                    + "id INTEGER NOT NULL PRIMARY KEY, "
                    + "category_id INTEGER, "
                    + "name VARCHAR(100), "
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
    private void initializeIncome(){
  try {
            Connection connection = connect();

            PreparedStatement createIncomeTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS income ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name varchar(255), "
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
    private void initializeCurrentBalance() {
        try {
            Connection connection = connect();

            PreparedStatement createBalanceTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS balance ("
                    + "id INTEGER NOT NULL PRIMARY KEY, "
                    + "category_id integer, "
                    + "expense_id integer, "
                    + "time DATE, "
                    + "amount float, "
                    + "FOREIGN KEY (category_id) REFERENCES category(id),"
                    + "FOREIGN KEY (expense_id) REFERENCES expense(id));"
            );
            createBalanceTable.execute();
            createBalanceTable.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }   private int getLastId(Connection connection) {
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
        
        ////GET ALL BALANCES, Budgets, or something
  //  }public List<MyBudget...> getAllBudgets() {
    //    List<Object> savedSimulations = new ArrayList<>();
     //   try {
       //     Connection connection = connect();
         //   PreparedStatement getAllBudgetsQuery = connection.prepareStatement("SELECT * FROM [TABLE NAME GOES HERE>..];");
           // ResultSet resultSet = geBudgetQuery.executeQuery();

      //      while (resultSet.next()) {
      //          Object o = new Object();
        //        o.setSimulationDetails(resultSet.getInt("id"), resultSet.getString("name"),  resultSet.getDate("startingDate").toLocalDate());
          //      saveBudgets.add(s);
//
    }
}

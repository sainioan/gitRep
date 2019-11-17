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
    private String dbaddress;
    public MyBudgetDatabase(String databaseAddress){
        this.dbaddress = databaseAddress;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbaddress);
}
      public void disconnect(Connection connection, PreparedStatement stmt, ResultSet rs) throws SQLException {
        rs.close();
        stmt.close();
        connection.close();
    }
      public void disconnect(Connection conn, PreparedStatement stmt) throws SQLException {
        stmt.close();
        conn.close();
    } private void initializeTables() {
        // The following methods will create required tables if they do not already exist in the database
       
        initializeCategories();
        initializeExpenses();
    }
      private void initializeCategories() {
        try {
            Connection conn = getConnection();

            PreparedStatement createCategoryTable = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Category ("
                    + "id integer PRIMARY KEY, "
                    + "plan_id integer, "
                    + "name varchar(255), "
                    + "allocated float, "
                    + "FOREIGN KEY (plan_id) REFERENCES Plan(id));"
            );
            createCategoryTable.execute();
            createCategoryTable.close();

            conn.close();
        } catch (SQLException e) {
            /* Do nothing */
        }
    }

    private void initializeExpenses() {
        try {
            Connection conn = getConnection();

            PreparedStatement createExpenseTable = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Expense ("
                    + "id integer PRIMARY KEY, "
                    + "category_id integer, "
                    + "name varchar(255), "
                    + "amount float, "
                    + "FOREIGN KEY (category_id) REFERENCES Category(id));"
            );
            createExpenseTable.execute();
            createExpenseTable.close();

            conn.close();
        } catch (SQLException e) {
            /* Do nothing */
        }
    }

}


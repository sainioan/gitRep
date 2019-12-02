/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import mybudgetapp.dao.MyBudgetDatabase;
import mybudgetapp.domain.Category;
import mybudgetapp.domain.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anniinasainio
 */
public class MyBudgetDatabaseTest {

    MyBudgetDatabase testdatabase;

    public MyBudgetDatabaseTest() throws ClassNotFoundException {
    }

    @Before
    public void setUp() throws IOException, SQLException, Exception {
        testdatabase = new MyBudgetDatabase("jdbc:sqlite:test.db");
        testdatabase.initializeDatabase();
    }

    @After
    public void tearDown() throws SQLException {

        Connection connection = testdatabase.connect();
        PreparedStatement stmt = connection.prepareStatement("DROP TABLE user");

        stmt.executeUpdate();
        stmt.close();
        connection.close();

        connection = testdatabase.connect();
        stmt = connection.prepareStatement("DROP TABLE category");
        stmt.executeUpdate();
        stmt.close();
        connection.close();

    }
    
    @Test
    public void initializeDatabaseReturnsTrueIfExcecuted() {
        
        assertEquals(true, testdatabase.initializeDatabase());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

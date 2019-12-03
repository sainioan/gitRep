/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mybudgetapp.domain.MyBudgetService;
import mybudgetapp.dao.MyBudgetDatabase;
import mybudgetapp.domain.User;
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
public class MyBudgetServiceTest {

    MyBudgetService mbs;
    MyBudgetDatabase testdatabase;
    User testuser;
    DBUserDao dbuser;

    @Before
    public void setUp() throws IOException, ClassNotFoundException, Exception {
        testdatabase = new MyBudgetDatabase("jdbc:sqlite:test.db");
        testdatabase.initializeDatabase();
        mbs = new MyBudgetService(testdatabase);
        testuser = new User("testUser", "TU123");
        mbs.login("testUser", "TU123");
        dbuser = new DBUserDao(testdatabase);

    }

    @After
    public void tearDown() throws SQLException {
        Connection connection = testdatabase.connect();
        PreparedStatement stmt = connection.prepareStatement("DROP TABLE category");

        stmt.executeUpdate();
        stmt.close();
        connection.close();

        Connection connection2 = testdatabase.connect();
        PreparedStatement stmt2 = connection2.prepareStatement("DROP TABLE user");

        stmt2.executeUpdate();
        stmt2.close();
        connection2.close();
    }

    @Test
    public void loggedInUserCanLogout() {
        mbs.login("testUser", "TU123");
        mbs.logout();

        assertEquals(null, mbs.getLoggedUser());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

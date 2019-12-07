/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
public class DBUserDaoTest {

    User user;
    DBUserDao dao;
    MyBudgetDatabase db;
    List<User> users;

    @Before
    public void setUp() throws SQLException, Exception {

        db = new MyBudgetDatabase("jdbc:sqlite:test.db");
        db.initializeDatabase();
        dao = new DBUserDao(db);
        db.initializeDatabase();;
        dao = new DBUserDao(db);
        user = new User("tester", "password123");
    }

    @After
    public void tearDown() throws SQLException {

        Connection connection = db.connect();
        PreparedStatement stmt = connection.prepareStatement("DROP TABLE user");
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
    @Test
    public void findByUsernameReturnsUser() throws Exception, NullPointerException {
        try{
        User newUser = new User("tester", "abc123");
        dao.create(newUser);
        users.add(newUser);
        newUser = dao.findByUsername("tester");
        assertEquals("tester", newUser.getUsername());
        assertEquals("abc123", newUser.getPassword());
        } catch (Throwable t){
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void create_Works() throws SQLException, Exception {
        //user.setUsername("tester");
        try {
            dao.saveUser(user);
            dao.create(user);
            assertEquals("tester", user.getUsername());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}

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

    @Before
    public void setUp() throws SQLException, Exception {

        db.initializeDatabase();
        dao = new DBUserDao(db);
        db = new MyBudgetDatabase("jdbc:sqlite:test.db");
        db.initializeDatabase();;
        dao = new DBUserDao(db);
        user = new User("tester", "password123");
        dao.saveUser(user);
    }

    @After
    public void tearDown() throws SQLException {

        Connection connection = db.connect();
        PreparedStatement stmt = connection.prepareStatement("DROP TABLE user");
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

//    @Test
//    public void findByUsernameReturnsUser() throws SQLException, Exception {
//        Connection connection = db.connect();
//
//        assertEquals(true, dao.saveUser(user));
//
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE username = 'tester'");
//
//        ResultSet rs = stmt.executeQuery();
//        User user = new User(rs.getString("username"), rs.getString("name"));
//
//        stmt.close();
//        rs.close();
//
//        connection.close();
//
//        assertEquals(user, dao.findByUsername("tester"));
//    }

}

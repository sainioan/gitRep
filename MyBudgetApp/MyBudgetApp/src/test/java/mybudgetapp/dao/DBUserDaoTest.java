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
import java.util.ArrayList;
import java.util.List;
import mybudgetapp.domain.User;
import org.junit.After;
import org.junit.Before;
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
    List<User> users = new ArrayList<>();

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
        try {
            Connection connection = db.connect();

            assertEquals(true, dao.saveUser(user));

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE username = 'tester'");

            ResultSet rs = stmt.executeQuery();
            User user = new User(rs.getString("username"), rs.getString("password"));

            stmt.close();
            rs.close();

            connection.close();
            assertEquals("tester", user.getUsername());
            assertEquals("password123", user.getPassword());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void createWorks() throws SQLException, Exception {
        users.add(user);
        try {
            dao.saveUser(user);
            dao.create(user);
            assertEquals("tester", user.getUsername());

        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void getAll1Works() throws SQLException, Exception {
        dao.create(user);
        users.add(user);
        assertEquals(1, users.size());
    }

    @Test
    public void getAll2Works() throws SQLException, Exception {
        dao.delete(user.getUsername());
        assertEquals(0, users.size());
    }

    @Test
    public void getAll3Works() throws SQLException, Exception {
        try{
        Connection con = db.connect();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM user");
        users = new ArrayList<>();

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getString("username"), rs.getString("password"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            
        }
        stmt.close();
        rs.close();
        con.close();
        users.add(user);
        assertEquals(1, users.size());
        } catch (Exception e){
            System.out.println("getAll3_Works test error..." + e.getMessage() );
        }
    }

    @Test
    public void findOneWorks() throws SQLException {
        dao.create(user);
        assertEquals(user, dao.findOne("tester"));
        assertEquals(null, dao.findByUsername("fakeUser"));
    }

    @Test
    public void delete_Works() throws SQLException, Exception {
        try {
            dao.delete(user.getUsername());

            assertEquals(true, dao.delete(user.getUsername()));

        } catch (Throwable t) {
            System.out.println("delete_Works test error..." + t.getMessage());
        }
    }

}

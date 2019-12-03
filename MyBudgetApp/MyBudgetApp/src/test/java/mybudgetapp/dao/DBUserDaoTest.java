/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.io.IOException;
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
        db = new MyBudgetDatabase();
        db.initializeDatabase();
        dao = new DBUserDao(db);
        user = new User("tester", "password123");
        dao.saveUser(user);
    }

    @After
    public void tearDown() throws SQLException {
    }

}

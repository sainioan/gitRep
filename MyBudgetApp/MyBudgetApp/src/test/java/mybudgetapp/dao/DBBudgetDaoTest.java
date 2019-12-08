/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import mybudgetapp.domain.Category;
import mybudgetapp.domain.MyBudget;
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
public class DBBudgetDaoTest {

    MyBudget myBudget;
    DBBudgetDao dao;
    MyBudgetDatabase db;
    Category testCategory;

    public DBBudgetDaoTest() {
    }

    @Before
    public void setUp() throws SQLException, Exception {
        db = new MyBudgetDatabase();
        db.initializeDatabase();
        dao = new DBBudgetDao(db);
        testCategory = new Category("tester", "testCategory");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void create_Works() throws SQLException {
        try {
            dao.saveCategory(testCategory);
            dao.create(testCategory);
            assertEquals("testCategory", testCategory.getName());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

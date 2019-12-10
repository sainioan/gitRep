/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import mybudgetapp.domain.Category;
import mybudgetapp.domain.Expense;
import mybudgetapp.domain.Income;
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

    DBBudgetDao dao;
    MyBudgetDatabase db;
    Category testCategory;
    Expense testExpense;
    Income testIncome;
    User testUser;

    private static final double DELTA = 1e-15;

    public DBBudgetDaoTest() {
    }

    @Before
    public void setUp() throws SQLException, Exception {
        db = new MyBudgetDatabase();
        db.initializeDatabase();
        dao = new DBBudgetDao(db);
        testUser = new User("tester", "testpw");
        testCategory = new Category("tester", "testCategory");
        testExpense = new Expense(testUser.getUsername(), testCategory.getName(), 50.50, LocalDate.now());
        testIncome = new Income(testUser.getUsername(), 1500.00, LocalDate.now());

    }

    @After
    public void tearDown() {
    }

    @Test
    public void create_Category_Works() throws SQLException {
        try {
            dao.saveCategory(testCategory);
            dao.create(testCategory);
            assertEquals("testCategory", testCategory.getName());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void create_Expense_Works() throws SQLException {
        try {
            dao.saveExpense(testExpense);
            dao.create(testExpense);
            assertEquals(testExpense.getAmount(), 50.50, DELTA);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void create_Income_Works() throws SQLException {
        try {
            dao.saveIncome(testIncome);
            dao.create(testIncome);
            assertEquals(testIncome.getAmount(), 1500.00, DELTA);
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

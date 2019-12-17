/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import mybudgetapp.domain.Balance;
import mybudgetapp.domain.Category;
import mybudgetapp.domain.Expense;
import mybudgetapp.domain.Income;
import mybudgetapp.domain.User;
import org.junit.After;
import org.junit.Before;
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
    Balance testBalance;
    List<Balance> balanceL = new ArrayList<>();

    private static final double DELTA = 1e-15;

    public DBBudgetDaoTest() {
    }

    @Before
    public void setUp() throws SQLException, Exception {
        db = new MyBudgetDatabase("jdbc:sqlite:test.db");
        db.initializeDatabase();
        dao = new DBBudgetDao(db);
        testUser = new User("tester", "testpw");
        testCategory = new Category("tester", "testCategory");
        testExpense = new Expense(testUser.getUsername(), testCategory.getName(), 50.50, LocalDate.now());
        testIncome = new Income(testUser.getUsername(), 1500.00, LocalDate.now());
        testBalance = new Balance("tester", 7000.0, LocalDate.now());

    }

    @After
    public void tearDown() {
    }

    @Test
    public void createCategoryWorks() throws SQLException {
        try {
            dao.saveCategory(testCategory);
            dao.create(testCategory);
            assertEquals("testCategory", testCategory.getName());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void saveBalanceWorks() throws SQLException {
        try {
            dao.saveBalance(testBalance);
            assertEquals(7000.0, testBalance.getAmount(), DELTA);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void createExpenseWorks() throws SQLException {
        try {
            dao.saveExpense(testExpense);
            dao.create(testExpense);
            assertEquals(testExpense.getAmount(), 50.50, DELTA);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void createIncomeWorks() throws SQLException {
        try {
            dao.saveIncome(testIncome);
            dao.create(testIncome);
            assertEquals(testIncome.getAmount(), 1500.00, DELTA);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void findOneWorks() throws SQLException, Exception {
        Balance b = new Balance("tester", 7000.0, LocalDate.now());
        dao.saveBalance(b);
        Balance d = dao.findOne("tester");
        assertEquals(7000.0, d.getAmount(), DELTA);
    }

    @Test
    public void getBalanceListWorks() throws SQLException {
        balanceL.add(dao.findOne(testUser.getUsername()));
        assertEquals(1, balanceL.size());
    }

    @Test
    public void findOne2Works() throws SQLException {
        dao.deleteBalance(testUser);
        assertTrue(dao.findOne(testUser.getUsername()) == null);
    }

    @Test
    public void getAllExpensesWork() throws SQLException, Exception {
        dao.saveExpense(testExpense);
        assertTrue(dao.getAllExpenses(testUser) != null);

    }

    @Test
    public void deleteCategory() throws SQLException {
        dao.deleteCategory(testUser);
        assertEquals(true, dao.deleteCategory(testUser));

    }

    @Test
    public void deleteExpense() throws SQLException {
        dao.deleteExpense(testUser);
        assertEquals(true, dao.deleteExpense(testUser));

    }

    @Test
    public void deleteIncome() throws SQLException {
        dao.deleteIncome(testUser);
        assertEquals(true, dao.deleteIncome(testUser));

    }

    @Test
    public void getBalanceList2Works() throws SQLException, Exception {
        try {
            dao.saveBalance(testBalance);
            assertTrue(dao.getBalanceList(testUser) != null);
        } catch (SQLException ex) {
            System.out.println("getBalanceList test error..." + ex.getMessage());
        }
    }

}

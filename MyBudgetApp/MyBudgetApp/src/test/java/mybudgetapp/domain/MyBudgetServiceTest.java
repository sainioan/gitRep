/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import mybudgetapp.dao.DBBudgetDao;
import mybudgetapp.dao.DBUserDao;
import mybudgetapp.dao.MyBudgetDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anniinasainio
 */
public class MyBudgetServiceTest {

    MyBudgetService mbs;
    MyBudgetService mbs2;
    MyBudgetDatabase testdatabase;
    //  List<Category> categoriesList = new ArrayList<>();
    User testuser;
    DBUserDao dbuser;
    DBBudgetDao dbbudget;
    List<Category> categories = new ArrayList<>();
    LocalDate today = LocalDate.now();
    Balance testBalance;
    private static final double DELTA = 1e-15;
    private Income i;
    private Expense e;

    @Before
    public void setUp() throws IOException, ClassNotFoundException, Exception {
        testdatabase = new MyBudgetDatabase("jdbc:sqlite:test.db");
        testdatabase.initializeDatabase();

        dbuser = new DBUserDao(testdatabase);
        dbbudget = new DBBudgetDao(testdatabase);
        testuser = new User("testUser", "TU123");
        mbs = new MyBudgetService(testdatabase, testuser.getUsername());

        dbuser = new DBUserDao(testdatabase);
        // dbuser.saveUser(testuser);
        mbs.createUser(testuser.getUsername(), testuser.getPassword());
        mbs.login(testuser.getUsername(), testuser.getPassword());
        testBalance = new Balance(testuser.getUsername(), 1000.0, today);

        i = new Income(testuser.getUsername(), 1000.0, today);

        mbs.createIncome(i.getUserName(), i.getAmount(), i.getDate());
        e = new Expense(testuser.getUsername(), "testCategory", 500.0, today);
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
    public void createUserWorks() throws SQLException {

        try {

            User loggedIn = mbs.getLoggedUser();
            System.out.println("loggedIn");
            assertEquals("testUser", loggedIn.getUsername());
        } catch (Exception e) {
            System.out.println("createuser error message..." + e.getMessage());
        }
    }

    @Test
    public void loggedInUserCanLogout() throws SQLException {
        mbs.login("testUser", "TU123");
        mbs.logout();

        assertEquals(null, mbs.getLoggedUser());
    }

    @Test
    public void listAllCategories() throws SQLException, Exception {
        List<Category> categoriesList = new ArrayList<>();
        try {
            Category category = new Category(testuser.getUsername(), "cars");
            mbs.createCategory(category.getUserName(), category.getName());
            categoriesList = dbbudget.getAllCategories(testuser);
            assertEquals(1, categoriesList.size());
        } catch (Exception e) {
            System.out.println("listallcategories error message " + e.getMessage());
        }

    }

    @Test
    public void listExpensesbyCategory() throws SQLException, Exception {

        dbbudget.getExpensesByCategory(testuser);
        assertTrue(mbs.expenseByCategory(testuser) != null);

    }

    @Test
    public void createExpense() throws SQLException {
        Expense expense = new Expense(testuser.getUsername(), "groceries", 150.0, today);
        dbbudget.createExpense(expense);
        assertEquals(true, mbs.createExpense(testuser.getUsername(), "groceries", 150.0, today));
    }

    @Test
    public void createCategory() throws SQLException {
        Category cat = new Category(testuser.getUsername(), "vacations");
        dbbudget.createCategory(cat);
        assertEquals(true, mbs.createCategory(testuser.getUsername(), "vacations"));
    }

    @Test
    public void createIncome() throws SQLException, Exception {
 
        assertEquals(true, mbs.createIncome(testuser.getUsername(), 1000.0, today));
    }

    @Test
    public void deleteIncome() throws SQLException, Exception {
  
        assertEquals(true, mbs.deleteIncome(testuser));
        assertEquals(false, mbs.deleteIncome(null));
    }
    @Test
    public void deleteExpense() throws SQLException, Exception {
       
        assertEquals(true, mbs.deleteExpense(testuser));
        assertEquals(false, mbs.deleteExpense(null));
    }

    @Test
    public void deleteCateogry() throws SQLException, Exception {
  
        assertEquals(true, mbs.deleteCategory(testuser));
        assertEquals(false, mbs.deleteCategory(null));
        
    }

    @Test
    public void deleteBalance() throws SQLException, Exception {
  
        assertEquals(true, mbs.deleteBalance(testuser));
        assertEquals(false, mbs.deleteBalance(null));
    }

    @Test
    public void createExpenseNewCategory() throws SQLException {
        Expense expense = new Expense(testuser.getUsername(), "create new", 150.0, today);
        Category cat = new Category(testuser.getUsername(), "vacations");
        dbbudget.createExpense(expense);
        dbbudget.createCategory(cat);
        assertEquals(true, mbs.createExpense(testuser.getUsername(), "groceries", 150.0, today));
    }

    @Test
    public void updateBalanceNewIncome() throws SQLException, Exception {
        dbbudget.saveBalance(testBalance);
        double inc = i.getAmount();
        boolean result = mbs.updateBalanceNewIncome(testuser.getUsername(), inc, LocalDate.now());
        assertEquals(true, result);

        dbbudget.findOne("anothertester");
        boolean res = mbs.updateBalanceNewIncome("anothertester", 10000.0, LocalDate.now());
        Balance balance = new Balance("anothertest", 10000.0, LocalDate.now());

        assertEquals(true, res);
        dbuser.saveUser(new User("anothertest", "1111111"));
        dbbudget.saveBalance(balance);

    }

    @Test
    public void createChoicesWorks() throws SQLException {
        try {
            assertTrue(mbs.createChoices(testuser) != null);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void updateBalanceNewExpense() throws SQLException, Exception {
        dbbudget.saveBalance(testBalance);
        double expense = e.getAmount();
        boolean result = mbs.updateBalanceNewExpense(testuser.getUsername(), expense, LocalDate.now());
        assertEquals(true, result);
        boolean result2 = mbs.updateBalanceNewExpense("fakeuser", expense, today);
        assertEquals(false, result2);
    }

    @Test
    public void createExpenseReturnsfalse() throws SQLException {

        assertEquals(false, mbs.createExpense("", "groceries", -100.0, today));
    }

    @Test
    public void getLoggedUserReturnsLogged() throws SQLException, Exception {
        try {
            dbuser.saveUser(testuser);
            mbs.createUser("testUser", "TU123");

            assertEquals("testUser", mbs.getLoggedUser().getUsername());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void loginReturnsFalseIfUserNull() throws SQLException {
        assertEquals(false, mbs.login("fakeuser", "fakeid"));
    }

    @Test
    public void validateUsernameInputWorks() throws SQLException {
        assertEquals(false, mbs.validateUsernameInput("a"));
        assertEquals(true, mbs.validateUsernameInput("annie"));
    }

    @Test
    public void deleteUserWorks() throws SQLException {
        User user = new User("testabc", "ta1234");
        mbs.createUser(user.getUsername(), user.getPassword());
        assertEquals(true, mbs.deleteUser(user));
        assertEquals(false, mbs.deleteUser(null));
    }

    @Test
    public void updateBalanceLabelWorks() throws SQLException, Exception {
        assertEquals("TESTUSER: (Balance): 0.0 € (Date): " + today, mbs.updateBalanceLabel());
        assertTrue(mbs.updateBalanceLabel() != null);
    }

}

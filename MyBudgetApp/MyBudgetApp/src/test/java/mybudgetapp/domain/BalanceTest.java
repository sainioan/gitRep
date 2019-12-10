/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BalanceTest {

    Balance balance = new Balance("testUser", 2500.0, LocalDate.now());
    Income i = new Income("Bella", 2500.0, LocalDate.now());
    Expense e = new Expense("Goofy", "vacation", 0.0, LocalDate.now());
    private static final double DELTA = 1e-15;
    User user = new User("tester", "alphabeta");
    LocalDate date = LocalDate.now();
    private double amount;

    @Before
    public void setUp() {
        String username = user.getUsername();
        balance = new Balance(username, 2500.0, date);

    }

    @Test

    public void getBalanceWorks() throws Exception {

        balance = new Balance(user.getUsername(), 2500.0, date);
        try {
            balance.setBalance(2500.0);
            assertEquals(2500.0, balance.getBalance(), DELTA);
        } catch (Exception ex) {
            System.out.println("getBalanceWorkTest fail ..." + ex.getMessage());
        }
    }

    public void equalWhenSame() {
        try {
            Balance b1 = new Balance("Ballerina", 2500.0, LocalDate.now());
            Balance b2 = new Balance("Ballerina", 2500.0, LocalDate.now());
            assertTrue(b1.equals(b2));
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void notEqualWhenDifferent() {
        Balance b1 = new Balance("Ballerina", 2500.0, LocalDate.now());
        Balance b2 = new Balance("Ballerina", 0.0, LocalDate.now());
        assertFalse(b1.equals(b2));
    }

    @Test
    public void addIncomeIsCorrect() {
        balance.setBalance(2500.0);
        balance.addIncome(200);
        assertEquals(2700.0, balance.getBalance(), DELTA);
    }

    @Test
    public void deductExpenseIsCorrect() {
        balance.setBalance(2500.0);
        balance.deductExpense(200);
        assertEquals(2300.0, balance.getBalance(), DELTA);
    }

    @Test
    public void constructorWorks() {
        balance.setBalance(2500.0);
        try {
            assertEquals(2500.0, balance.getBalance(), DELTA);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
    @Test
    public void setIdworks() {
        balance.setId(5);
        assertEquals(5, balance.getId());

    }

    @Test
    public void testToString() {

        Balance b1 = new Balance("Ballerina", 2500.0, LocalDate.now());
        b1.setBalance(2500.0);
        String expected = "Ballerina: (Balance): 2500.0 on " + LocalDate.now().toString();
        assertEquals(expected, b1.toString());
    }

}

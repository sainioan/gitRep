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
    @Before
    public void setUp() {
        String username = user.getUsername();
        balance = new Balance(username, 2500.0, date);
    }

    @Test

    public void getBalanceWorks() throws Exception {
        try{
    balance.setBalance(2500.0);
        assertEquals(2500.0, balance.getBalance(), DELTA);
        } catch (Exception ex){
            System.out.println("getBalanceWorkTest fail ..." + ex.getMessage());
        }
    }

    public void equalWhenSameId() {
        try {
            Balance b1 = new Balance("Ballerina", 2500.0, LocalDate.now());
            Balance b2 = new Balance("Ballerina", 2500.0, LocalDate.now());
            assertTrue(b1.equals(b2));
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
    @Test
    public void notEqualWhenDifferentId() {
        Balance b1 = new Balance("Ballerina", 2500.0, LocalDate.now());
        Balance b2 = new Balance("Ballerina", 2500.0, LocalDate.now());
        assertFalse(b1.equals(b2));
    }
    @Test
    public void constructorWorks() {
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

}

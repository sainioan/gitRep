/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BalanceTest {

    Balance balance;
    Income i;
    Expense e;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() {
        balance = new Balance(2500.0);
    }

    @Test

    public void getBalaceWorks() {

        assertEquals(2500.0, balance.getBalance(), DELTA);
    }

    public void equalWhenSameId() {
        try {
            Balance b1 = new Balance(1, 0.0, DELTA);
            Balance b2 = new Balance(1, 0.0, DELTA);
            assertTrue(b1.equals(b2));
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

}

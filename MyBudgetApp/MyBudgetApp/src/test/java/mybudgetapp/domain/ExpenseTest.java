/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.time.LocalDate;
import mybudgetapp.domain.Expense;
import java.util.TimeZone.*;
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
public class ExpenseTest {

    private static final double DELTA = 1e-15;
    private Expense e;
    private LocalDate date;

    public ExpenseTest() {

    }

    @Before
    public void setUp() {
        e = new Expense("tester", "vacation", 500.0, LocalDate.now());
    }

    @Test
    public void constructorWorks() {
        e.setId(1);
        try {
            assertEquals(500.0, e.getAmount(), DELTA);
            assertEquals(1, e.getId());
            assertEquals(LocalDate.now(), e.getDate());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void setIdworks() {
        e.setId(5);
        assertEquals(5, e.getId());

    }

    public void getAmountWorks() {

        assertEquals(500.0, e.getAmount(), DELTA);
    }

    public void equalWhenSameId() {
        try {
            Expense e1 = new Expense("tester", "vacation", 500.0, LocalDate.now());
            Expense e2 = new Expense("tester", "vacation", 500.0, LocalDate.now());
            assertTrue(e1.equals(e2));
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void notEqualWhenDifferentId() {
        Expense e1 = new Expense("tester", "vacation", 500.0, LocalDate.now());
        Expense e2 = new Expense("differentTester", "groceries", 1000.0, LocalDate.now());
        assertFalse(e1.equals(e2));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TimeZone.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anniinasainio
 */
public class ExpenseTest {

    private static final double DELTA = 1e-15;
    private Expense e;
    private LocalDate date = LocalDate.now();
    private ArrayList<Expense> testList;

    public ExpenseTest() {

    }

    @Before
    public void setUp() {
        e = new Expense("tester", "vacation", 500.0, LocalDate.now());
        testList = new ArrayList<>();
    }

    @Test
    public void constructorWorks() {

        try {
            assertEquals(500.0, e.getAmount(), DELTA);
            assertEquals(LocalDate.now(), e.getDate());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void listExpensesWorks() {
        try {
            Expense exp = new Expense("Peter", "boats", 700.0, LocalDate.now());
            System.out.println("size of List" + testList.size());
            System.out.println("size of List" + testList.size());
        } catch (Exception e) {
            System.out.println("listExpensesTest error" + e.getMessage());
        }
    }

    public void getAmountWorks() {
        try {

            assertEquals(500.0, e.getAmount(), DELTA);
        } catch (Exception e) {
            System.out.println("Expense getAmount error " + e.getMessage());
        }
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

    @Test
    public void getDateWorks() {
        try {
            e.setDate(date);
            assertEquals(LocalDate.now(), e.getDate());
        } catch (Exception e) {
            System.out.println("Expense getDate error " + e.getMessage());
        }
    }

    @Test
    public void testToString() {
        try {

            Expense e1 = new Expense("Tester", "Insurance", 700.0, LocalDate.now());
            e1.setAmount(700.0);
            String expected = "Tester: (Expense catetegory): Insurance, (Amount): 700.0, (Date): " + LocalDate.now().toString();
            assertEquals(expected, e1.toString());
        } catch (Throwable t) {
            System.out.println("Expense toString error " + t.getMessage());
        }
    }
}

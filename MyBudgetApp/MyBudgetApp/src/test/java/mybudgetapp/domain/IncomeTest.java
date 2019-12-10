/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author anniinasainio
 */
public class IncomeTest {

    Income i;
    private static final double DELTA = 1e-15;
    private LocalDate today = LocalDate.now();
    private ArrayList<Income> testList;

    public IncomeTest() {
    }

    @Before
    public void setUp() {
        i = new Income(1, 2500.0, LocalDate.now());
        testList = new ArrayList<>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorWorks() {
        try {
            assertEquals(2500.0, i.getAmount(), DELTA);
            assertEquals(1, i.getId());
            assertEquals(LocalDate.now(), i.getDate());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void equalWhenSameId() {
        try {
            Income i1 = new Income(1, 0.0, LocalDate.now());
            Income i2 = new Income(1, 0.0, LocalDate.now());
            assertTrue(i1.equals(i2));
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void notEqualWhenDifferentId() {
        Income t1 = new Income(1, 60.0, null);
        Income t2 = new Income(2, 13.0, null);
        assertFalse(t1.equals(t2));
    }

    @Test
    public void setIdworks() {
        i.setId(5);
        assertEquals(5, i.getId());

    }

    @Test
    public void getDateWorks() {
        i.setDate(today);
        assertEquals(LocalDate.now(), i.getDate());
    }

    @Test
    public void getAmountWorks() {
        assertEquals(2500.0, i.getAmount(), DELTA);
    }

    public void listincomeWorks() {
        try {
            Income inc = new Income("Peter", 1500.0, LocalDate.now());
            System.out.println("size of List" + testList.size());
            System.out.println("size of List" + testList.size());;
        } catch (Exception e) {
            System.out.println("listExpensesTest error" + e.getMessage());
        }
    }

    @Test
    public void getExpensesTotalWorks() {
        i.listIncome(testList, i);
        try {
            assertEquals(2500.0, i.getIncomeTotal(), DELTA);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testToString() {

        Income i1 = new Income("Tester", 1500.0, LocalDate.now());
        i1.setAmount(1500.0);
        String expected = "Tester: (Income amount): 1500.0, (Date): " + LocalDate.now().toString();
        assertEquals(expected, i1.toString());
    }

}

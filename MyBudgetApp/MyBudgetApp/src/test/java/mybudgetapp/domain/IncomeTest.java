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
import java.time.LocalDate;

/**
 *
 * @author anniinasainio
 */
public class IncomeTest {

    Income i;
    private static final double DELTA = 1e-15;

    public IncomeTest() {
    }

    @Before
    public void setUp() {
        i = new Income(2500.0, LocalDate.now());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAmountWorks() {
        assertEquals(2500.0, i.getAmount(), DELTA);
    }
}

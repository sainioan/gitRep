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
import mybudgetapp.domain.MyBudget;

/**
 *
 * @author anniinasainio
 */
public class MyBudgetTest {

    private static final double DELTA = 1e-15;
    MyBudget mb;

    public MyBudgetTest() {
    }

    @Before
    public void setUp() {
        mb = new MyBudget("barbados", 1000.0);
  
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void notEqualWhenDifferentId() {
        try{
        MyBudget m1 = new MyBudget("week", 0.0);
        MyBudget m2 = new MyBudget("vacay", 1000.00);
        assertFalse(m1.equals(m2));
        } catch (Throwable t){
            System.out.println(t.getMessage());
        }
    }

}


// TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}


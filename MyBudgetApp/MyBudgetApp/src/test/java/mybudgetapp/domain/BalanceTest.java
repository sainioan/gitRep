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
        balance=  new Balance(2500.0);
    }
    
    @Test
     
    public void getBalaceWorks() {
        
        assertEquals(2500.0, balance.getBalance(),DELTA);
    }
//    public void equalWhenSameUsername() {
//        User user1 = new User("Tanya", "Hall75");
//        User user2 = new User("Tanya", "Hall75");
//        assertTrue(user1.equals(user2));
//    }
//    
//    @Test
//    public void nonEqualWhenDifferentUsername() {
//        User user1 = new User("Tanya", "Hall75");
//        User user2 = new User("Alistair", "McKenna59");
//        assertFalse(user1.equals(user2));
//    } 
//    
//    @Test
//    public void nonEqualWhenDifferentType() {
//        User user = new User("Tanya", "Hall72");
//        Object obj = new Object();
//        assertFalse(user.equals(obj));
//    }   @Test
//    public void users_Password_IsCorrect() {
//        assertEquals("AtOIC2020", user.getPassword());
   // }    
}
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

/**
 *
 * @author anniinasainio
 */
public class UserTest {

    User user;

    public UserTest() {
    }

    @Before
    public void setUp() {
        user = new User("Mamiko", "AtOIC2020");
    }

    @Test
    public void equalWhenSameUsername() {
        User user1 = new User("Tanya", "Hall75");
        User user2 = new User("Tanya", "Hall75");
        assertTrue(user1.equals(user2));
    }

    @Test
    public void nonEqualWhenDifferentUsername() {
        User user1 = new User("Tanya", "Hall75");
        User user2 = new User("Alistair", "McKenna59");
        assertFalse(user1.equals(user2));
    }

    @Test
    public void nonEqualWhenDifferentType() {
        User user = new User("Tanya", "Hall72");
        Object obj = new Object();
        assertFalse(user.equals(obj));
    }

    @Test
    public void usersPasswordIsCorrect() {
        assertEquals("AtOIC2020", user.getPassword());
    }

    @Test
    public void constructorWorks() {
        try {
            assertEquals("Mamiko", user.getUsername());
            assertEquals("AtOIC2020", user.getPassword());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}

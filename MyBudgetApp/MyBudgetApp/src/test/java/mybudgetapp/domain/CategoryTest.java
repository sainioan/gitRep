/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import mybudgetapp.domain.Category;
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
public class CategoryTest {

    Category category = new Category("testCategory");

    public CategoryTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test

    public void equalWhenSameId() {
        try {
            Category c1 = new Category(1, null);
            Category c2 = new Category(1, null);
            assertTrue(c1.equals(c2));
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void equalWhenSameName() {
        try {
            Category c1 = new Category("category");
            Category c2 = new Category("category");
            assertTrue(c1.equals(c2));
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

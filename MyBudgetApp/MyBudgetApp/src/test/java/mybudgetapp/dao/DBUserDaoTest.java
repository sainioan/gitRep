/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import mybudgetapp.domain.User;
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
public class DBUserDaoTest {
    User user;
    DBUserDao dao;
    MyBudgetDatabase db;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//    @Before
//    public void setUp() throws SQLException {
//        db = new MyBudgetDatabase();
//        db.initializeDatabase();
//        dao = new DBUserDao(db);
//        user = new User("tester", "password123");
//        dao.saveOrUpdate(user);
//    }
//
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    /**
//     * Test of findAll method, of class DBUserDao.
//     */
//    @Test
//    public void testFindAll() throws Exception {
//        System.out.println("findAll");
//        DBUserDao instance = null;
//        List<User> expResult = null;
//        List<User> result = instance.findAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of save method, of class DBUserDao.
//     */
//    @Test
//    public void testSave() throws Exception {
//        System.out.println("save");
//        DBUserDao instance = null;
//        instance.save();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveOrUpdate method, of class DBUserDao.
//     */
//    @Test
//    public void testSaveOrUpdate() throws Exception {
//        System.out.println("saveOrUpdate");
//        User user = null;
//        DBUserDao instance = null;
//        User expResult = null;
//        User result = instance.saveOrUpdate(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findOne method, of class DBUserDao.
//     */
//    @Test
//    public void testFindOne() throws Exception {
//        System.out.println("findOne");
//        String username = "";
//        DBUserDao instance = null;
//        User expResult = null;
//        User result = instance.findOne(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class DBUserDao.
//     */
//    @Test
//    public void testDelete() throws Exception {
//        System.out.println("delete");
//        String username = "";
//        DBUserDao instance = null;
//        instance.delete(username);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of create method, of class DBUserDao.
//     */
//    @Test
//    public void testCreate() throws Exception {
//        System.out.println("create");
//        User user = null;
//        DBUserDao instance = null;
//        User expResult = null;
//        User result = instance.create(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findByUsername method, of class DBUserDao.
//     */
//    @Test
//    public void testFindByUsername() {
//        System.out.println("findByUsername");
//        String username = "";
//        DBUserDao instance = null;
//        User expResult = null;
//        User result = instance.findByUsername(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAll method, of class DBUserDao.
//     */
//    @Test
//    public void testGetAll() {
//        System.out.println("getAll");
//        DBUserDao instance = null;
//        List<User> expResult = null;
//        List<User> result = instance.getAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}

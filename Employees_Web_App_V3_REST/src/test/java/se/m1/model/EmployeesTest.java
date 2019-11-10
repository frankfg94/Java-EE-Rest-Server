/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.model;

import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author franc
 */
public class EmployeesTest {
    
    public EmployeesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Employees.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Employees instance = new Employees();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Employees.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 1;
        Employees instance = new Employees();
        instance.setId(id);        
    }

    /**
     * Test of getName method, of class Employees.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Employees instance = new Employees();
        instance.setName("testName");
        String expResult = "testName";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Employees.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "allo";
        Employees instance = new Employees();
        instance.setName(name);        
    }

    /**
     * Test of getFirstname method, of class Employees.
     */
    @Test
    public void testGetFirstname() {
        System.out.println("getFirstname");
        Employees instance = new Employees();
        instance.setFirstname("marine");
        String expResult = "marine";
        String result = instance.getFirstname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstname method, of class Employees.
     */
    @Test
    public void testSetFirstname() {
        System.out.println("setFirstname");
        Employees instance = new Employees();
        instance.setFirstname("marc");
    }

    /**
     * Test of getTelhome method, of class Employees.
     */
    @Test
    public void testGetTelhome() {
        System.out.println("getTelhome");
        Employees instance = new Employees();
        instance.setTelhome("07");
        String expResult = "07";
        String result = instance.getTelhome();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTelhome method, of class Employees.
     */
    @Test
    public void testSetTelhome() {
        System.out.println("setTelhome");
        String telhome = "0695676314";
        Employees instance = new Employees();
        instance.setTelhome(telhome);
        
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getTelmob method, of class Employees.
     */
    @Test
    public void testGetTelmob() {
        System.out.println("getTelmob");
        Employees instance = new Employees();
        instance.setTelmob("0695676314");
        String expResult = "0695676314";
        String result = instance.getTelmob();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTelmob method, of class Employees.
     */
    @Test
    public void testSetTelmob() {
        System.out.println("setTelmob");
        String telmob = "";
        Employees instance = new Employees();
        instance.setTelmob(telmob);
    }

    /**
     * Test of getTelpro method, of class Employees.
     */
    @Test
    public void testGetTelpro() {
        System.out.println("getTelpro");
        Employees instance = new Employees();
        instance.setTelpro("1234");
        String expResult = "1234";
        String result = instance.getTelpro();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTelpro method, of class Employees.
     */
    @Test
    public void testSetTelpro() {
        System.out.println("setTelpro");
        String telpro = "07";
        Employees instance = new Employees();
        instance.setTelpro(telpro);
        assertTrue(telpro.equals(instance.getTelpro()));
    }

    /**
     * Test of getAdress method, of class Employees.
     */
    @Test
    public void testGetAdress() {
        System.out.println("getAdress");
        Employees instance = new Employees();
        instance.setAdress("Poland");
        String expResult = "Poland";
        String result = instance.getAdress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdress method, of class Employees.
     */
    @Test
    public void testSetAdress() {
        System.out.println("setAdress");
        String adress = "Alesia";
        Employees instance = new Employees();
        instance.setAdress(adress);
    }

    /**
     * Test of getPostalcode method, of class Employees.
     */
    @Test
    public void testGetPostalcode() {
        System.out.println("getPostalcode");
        Employees instance = new Employees();
        instance.setPostalcode("Fort Knox, US");
        String expResult = "Fort Knox, US";
        String result = instance.getPostalcode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPostalcode method, of class Employees.
     */
    @Test
    public void testSetPostalcode() {
        System.out.println("setPostalcode");
        String postalcode = "74";
        Employees instance = new Employees();
        instance.setPostalcode("74");
        instance.setPostalcode(postalcode);
    }

    /**
     * Test of getCity method, of class Employees.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        Employees instance = new Employees();
        instance.setCity("EuroCity");
        String expResult = "EuroCity";
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class Employees.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String city = "Istambul";
        Employees instance = new Employees();
        instance.setCity(city);
    }

    /**
     * Test of getEmail method, of class Employees.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Employees instance = new Employees();
        instance.setEmail("gaben@valvesoftware.com");
        String expResult = "gaben@valvesoftware.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Employees.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "testdemail@live.fr";
        Employees instance = new Employees();
        instance.setEmail(email);
    }



    /**
     * Test of equals method, of class Employees.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Employees instance = new Employees();
        Employees instance2 = new Employees();
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }


}

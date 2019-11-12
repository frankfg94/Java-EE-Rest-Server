///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package se.m1.beans;
//
// import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//import javax.ejb.embeddable.EJBContainer;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import se.m1.model.Employees;
//
///**
// *
// * @author franc
// */
//public class EmployeesSBTest {
//
//    private static EJBContainer container;
//    private static boolean started = false;
//    public EmployeesSBTest() {
//
//    }
//    
//    @BeforeClass
//        // We store the container only one time to gain performances
//    public  static void setUpClass() throws IOException {
//        if(!started)
//        {   
//            started = true;
//            container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        }
//    }
//    
//    @AfterClass
//        // We free the resources when all the tests are done
//    public  static void tearDownClass() {    
//        container.close();
//    }
//    
//    @Before
//    public void setUp() {
//
//    }
//    
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getAllEmployees method, of class EmployeesSB.
//     */
//    @Test
//    public void testGetAllEmployees() throws Exception {
//        System.out.println("getAllEmployees");
//        EmployeesSB instance = (EmployeesSB)container.getContext().lookup("java:global/classes/EmployeesSB");
//        List expResult = null;
//        List result = instance.getAllEmployees();
//    }
//
//    /**
//     * Test of getAllEmployeesDict method, of class EmployeesSB.
//     */
//    @Test
//    public void testGetAllEmployeesDict() throws Exception {
//        System.out.println("getAllEmployeesDict");
//        EmployeesSB instance = (EmployeesSB)container.getContext().lookup("java:global/classes/EmployeesSB");
//        TreeMap<Integer, Employees> expResult = null;
//        TreeMap<Integer, Employees> result = instance.getAllEmployeesDict();
//        assertEquals(expResult, null);
//        // TODO review the generated test code and remove the default call to fail.
//    }
//
//    /**
//     * Test of AddEmployee method, of class EmployeesSB.
//     */
//    @Test
//    public void testCRUDEmployee() throws Exception {
//        Map<String, Object> props = new HashMap<String, Object>();
//        props.put("org.glassfish.ejb.embedded.glassfish.installation.root", "C:\\Users\\franc\\.m2\\repository\\org\\glassfish");
//        System.out.println("AddEmployee");
//        EmployeesSB instance = (EmployeesSB)container.getContext().lookup("java:global/classes/EmployeesSB");
//        Employees emp = new Employees();
//        emp.setEmail("thisEmailWillBeChanged@live.fr");
//        emp.setFirstname("François");
//        emp.setName("Gillioen");
//        emp.setPostalcode("94100");
//        emp.setTelhome("/");
//        emp.setTelpro("/");
//        emp.setTelmob("/");
//        emp.setCity("Paris");
//        emp.setAdress("/");
//        System.out.println("Beginning operations");
//        instance.addEmployee(emp);
//        System.out.println("Employee Added");
//        emp.setEmail("This a modified mail");
//        instance.EditEmployee(emp);
//        System.out.println("Employee edited");
//        instance.RemoveEmployee(emp);
//        System.out.println("Employee deleted successfuly");
//        // TODO review the generated test code and remove the default call to fail.
//    }
//
//}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package se.m1.beans;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.ejb.embeddable.EJBContainer;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author franc
// */
//public class UsersSBTest {
//    
//    public UsersSBTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getAllUsers method, of class UsersSB.
//     */
//    @Test
//    public void testGetAllUsers() throws Exception {
//        System.out.println("getAllUsers");
//        Map properties = new HashMap();
//        
//        // etrange  
//  properties.put(EJBContainer.MODULES, new File("target/classes"));  
//  properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "C:\\Program Files\\glassfish-4.1.1\\glassfish");
//  
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
//        UsersSB instance = (UsersSB)container.getContext().lookup("java:global/classes/UsersSB");
//        List expResult = null;
//        List result = instance.getAllUsers();
//        assertEquals(expResult, result);
//        container.close();
//    }
//    
//}

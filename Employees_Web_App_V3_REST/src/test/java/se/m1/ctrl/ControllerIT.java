/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.servlets.CGIServlet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.MockitoAnnotations;


/**
 *
 * @author franc
 */
public class ControllerIT extends Mockito{
    
    public ControllerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() {
    }

    private final Controller servlet = new Controller();
    /**
     * Test of processRequest method, of class Controller.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");

        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class); 
        
        servlet.doPost(request, response);
        new LoginPageActions().processRequest(request, response, servlet, servlet.empSB, servlet.usersSB);
        
        // TODO review the generated test code and remove the default call to fail.
    }




}

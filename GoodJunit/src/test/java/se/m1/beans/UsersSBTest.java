/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.beans;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.m1.ctrl.Controller;
import se.m1.model.Users;

/**
 *
 * @author franc
 */
public class UsersSBTest {
    
    public UsersSBTest() {
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
     * Test of getAllUsers method, of class UsersSB.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        Map<String, Object> props =new HashMap<>();
       //props.put("org.glassfish.ejb.embedded.glassfish.installation.root","C:\\Program Files\\glassfish-4.1.1\\glassfish");
        //props.put(EJBContainer.MODULES, new File("target/classes"));
        props.put(EJBContainer.PROVIDER, "org.glassfish.ejb.embedded.EJBContainerProviderImpl");
       // props.put("org.glassfish.ejb.embedded.glassfish.configuration.file", "C:\\Users\\franc\\Desktop\\org\\glassfish\\embed\\domain.xml");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(props);
        UsersSB instance = (UsersSB)container.getContext().lookup("java:global/classes/UsersSB!se.m1.beans.UsersSB");
        List<Users> result = instance.getAllUsers();
        assertTrue(result.size() > 0);
        System.out.println("The following list of users has been retrieved : ");
        for(Users e : result)
        {
            System.out.println(e.getId() + " | " + e.getLogin() + " | " + e.getPwd() + " | " + e.getRole());
        }
        container.close();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}

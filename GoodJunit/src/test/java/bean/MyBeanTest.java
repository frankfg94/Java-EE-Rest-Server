/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
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
public class MyBeanTest {
    
    public MyBeanTest() {
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
     * Test of addNumbers method, of class MyBean.
     */
    @Test
    public void testAddNumbers() throws Exception {
        System.out.println("addNumbers");
        int numberA = 2;
        int numberB = 3;
       Map<String, Object> props =new HashMap<String, Object>();
        props.put(EJBContainer.PROVIDER, "org.glassfish.ejb.embedded.EJBContainerProviderImpl");
        props.put("org.glassfish.ejb.embedded.glassfish.configuration.file", "C:\\Users\\franc\\.m2\\repository\\org\\glassfish\\extras\\glassfish-embedded-all\\3.1.1\\glassfish-embedded-all-3.1.1.jar\\org\\glassfish\\embed\\domain.xml");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MyBean instance = (MyBean)container.getContext().lookup("java:global/classes/MyBean");
        int expResult = 5;
        int result = instance.addNumbers(numberA, numberB);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}

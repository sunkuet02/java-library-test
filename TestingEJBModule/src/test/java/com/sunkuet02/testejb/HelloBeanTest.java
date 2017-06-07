package com.sunkuet02.testejb;

import com.sunkuet02.testejb.testutils.Utils;
import org.junit.*;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by sun on 5/7/17.
 */
public class HelloBeanTest {

    private static EJBContainer container;

    @Before
    public void setUp() {
        Properties containerProperties = Utils.getDbProperties();
        containerProperties.put(EJBContainer.MODULES, new File("target/classes"));
        container = EJBContainer.createEJBContainer(containerProperties);
    }

    @After
    public void tearDown() {
        if(container != null) {
            container.close();
        }
    }

    /**
     * Test of sayHello method, of class HelloBean.
     */
    @Test
    public void testSayHello() throws Exception {
        String name = "sun";
        HelloBeanLocal instance = (HelloBeanLocal)container.getContext().lookup("java:global/TestingEJBModule/HelloBean!com.sunkuet02.testejb.HelloBeanLocal");

        String expResult = "hello sun";
        String result = instance.sayHello(name);
        assertEquals(expResult, result);
    }

}
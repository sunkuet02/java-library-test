package com.sunkuet02.testejb.service;

import com.sunkuet02.testejb.entity.User;
import com.sunkuet02.testejb.testutils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by sun on 5/8/17.
 */
public class UserServiceTest {
    private EJBContainer container;

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

    @Test
    public void testAddUser() throws Exception {
        UserService userService = (UserService)container.getContext().lookup("java:global/TestingEJBModule/UserServiceImpls!com.sunkuet02.testejb.service.UserService");
        assertNotNull(userService);

        User user = new User("sun");
        userService.addUser(user);

        assertEquals(true, user.getId() > 0);
        assertEquals(1, userService.getAll().size());
    }

}
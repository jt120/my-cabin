package com.jt.service;

import com.jt.bean.User;
import com.jt.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * since 2015/2/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setName("test3");
        user.setPassword("123");
        long add = userService.add(user);
        Assert.assertTrue(add > 0);
    }

    @Test
    public void testFind() throws Exception {
        User hello = userService.findByName("hello");
        Assert.assertNotNull(hello);
        Assert.assertTrue(hello.getId() == 1);
    }

}

package com.jt.dao;

import com.jt.bean.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

/**
 * since 2015/2/26.
 */
public class UserDaoImplTest {
    UserDao userDao = null;

    @Before
    public void init() {
        userDao = new UserDaoImpl();
    }

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setName("test2");
        user.setPassword("123");

        long id = userDao.add(user);

        Assert.assertTrue("not > 0", id > 0);
    }

    @Test
    public void testFind() throws Exception {
        User hello = userDao.findByName("hello");
        Assert.assertNotNull(hello);
        Assert.assertEquals(1, hello.getId());
    }
}

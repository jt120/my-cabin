package com.jt.service;

import com.jt.bean.Order;
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
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testAdd() throws Exception {
        Order order = new Order();
        User user = new User();
        user.setId(1);

        order.setUser(user);
        long add = orderService.add(order);
        Assert.assertTrue(add > 0);
    }
}

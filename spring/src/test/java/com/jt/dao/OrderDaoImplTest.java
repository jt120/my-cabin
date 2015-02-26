package com.jt.dao;

import com.jt.bean.Order;
import com.jt.bean.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * since 2015/2/26.
 */
public class OrderDaoImplTest {

    OrderDao orderDao = null;

    @Before
    public void init() {
        orderDao = new OrderDaoImpl();
    }

    @Test
    public void testAdd() throws Exception {
        UserDao userDao = new UserDaoImpl();
        User hello = userDao.findByName("hello");
        Order order = new Order();
        order.setOrderNo("123");
        order.setUser(hello);

        long id = orderDao.add(order);

        Assert.assertTrue(id > 0);
    }

    @Test
    public void testFind() throws Exception {
        Order byOrderNo = orderDao.findByOrderNo("123");
        Assert.assertNotNull(byOrderNo);

        Assert.assertEquals(1, byOrderNo.getUserId());
    }

    @Test
    public void testFindByUserId() throws Exception {
        List<Order> orders = orderDao.findByUserId(1);
        Assert.assertNotNull(orders);

        Assert.assertTrue(orders.size() > 0);
    }
}

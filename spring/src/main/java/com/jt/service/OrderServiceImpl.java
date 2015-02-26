package com.jt.service;

import com.jt.bean.Order;
import com.jt.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by ze.liu on 2014/7/2.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public long add(Order order) {
        String replace = UUID.randomUUID().toString().replace("-", "");
        order.setOrderNo(replace);
        return orderDao.add(order);
    }

    @Override
    public Order findByOrderNo(String orderNo) {
        return orderDao.findByOrderNo(orderNo);
    }

    @Override
    public List<Order> findByUserId(long userId) {
        return orderDao.findByUserId(userId);
    }
}

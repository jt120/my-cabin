package com.jt.dao;

import com.jt.bean.Order;

import java.util.List;

/**
 * since 2015/2/26.
 */
public interface OrderDao {

    long add(Order order);

    Order findByOrderNo(String orderNo);

    List<Order> findByUserId(long userId);
}

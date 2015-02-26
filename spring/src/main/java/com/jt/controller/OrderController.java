package com.jt.controller;

import com.jt.bean.Order;
import com.jt.bean.User;
import com.jt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * since 2015/2/26.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public String create(HttpSession session) {
        Order order = new Order();
        User u = (User) session.getAttribute("loginUser");
        order.setUser(u);

        long add = orderService.add(order);
        return "redirect:/index";
    }
}

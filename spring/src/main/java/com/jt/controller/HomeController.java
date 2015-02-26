package com.jt.controller;

import com.jt.bean.Order;
import com.jt.bean.User;
import com.jt.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * since 2015/2/26.
 */
@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping("/index")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        log.info("login user {}", user);
        if (user == null) {
            return "redirect:/user/login";
        }
        List<Order> orders = orderService.findByUserId(user.getId());
        log.info("find orders {}", orders);
        model.addAttribute("orders", orders);
        return "home";
    }
}

package com.jt.web.controller;

import com.jt.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * since 2015/2/5.
 */
@Controller
@RequestMapping("/jsp")
public class JspController {

    @RequestMapping("/hello")
    public String hello(Model model) {
        User user = new User("hello", "1234");
        model.addAttribute("user", user);
        return "hello";
    }
}

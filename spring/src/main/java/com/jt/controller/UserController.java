package com.jt.controller;

import com.jt.bean.User;
import com.jt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * since 2015/2/26.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, Model model, HttpSession session) {
        User byName = userService.findByName(user.getName());
        log.info("find user {}", byName);
        if (byName != null) {
            model.addAttribute("error", "用户已存在");
            return "user/register";
        }
        long add = userService.add(user);
        if (add > 0) {
            session.setAttribute("loginUser", user);
            return "redirect:/index";
        }
        model.addAttribute("error", "系统错误");
        return "user/register";
    }

    @RequestMapping("/register")
    public String register() {
        return "user/register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session, Model model) {
        if (user == null) {
            return "user/login";
        }
        User byName = userService.findByName(user.getName());
        if (byName.getPassword().equals(user.getPassword())) {
            log.info("login in user {}", byName);
            session.setAttribute("loginUser", byName);
            return "redirect:/index";
        }
        model.addAttribute("error", "用户名或密码错误");
        return "user/login";
    }

    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }
}

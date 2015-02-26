package com.jt.web.simple;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ze.liu on 2014/9/2.
 */
@Controller
public class SimpleController {

    @RequestMapping("hello")
    public String hello(HttpServletRequest request, Model model) {
        System.out.println(request.getParameter("name"));
        model.addAttribute("stu", request.getParameter("name"));
        return "hello";
    }
}

package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.ref.ReferenceQueue;
import java.util.Map;

/**
 * since 2015/2/10.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/test")
    public String hello(Model model) {
        model.addAttribute("name", null);
        model.addAttribute("age", 13);
        return "hello";
    }


    @RequestMapping("/test2")
    @ResponseBody
    public String test(HttpServletRequest request) {

        String name = request.getParameter("name");
        System.out.println("1 " + name);
        Map parameterMap = request.getParameterMap();
        parameterMap.put("name", new String[]{"ggg"});
        String name1 = request.getParameter("name");
        System.out.println("2 " + name1);
        return name1;
    }
}

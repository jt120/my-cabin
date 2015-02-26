package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ze.liu
 * @since 2014/6/23
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @RequestMapping("/add")
    public String add() {
        return "article/add";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(HttpServletRequest request) {
        String content = request.getParameter("content");
        String title = request.getParameter("title");

        System.out.println("content: " + content);
        System.out.println("content length: " + content.length());

        return "content length: " + content.length();
    }
}

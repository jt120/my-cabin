package com.jt.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author ze.liu
 * @since 2014/5/28
 */
public class WelcomeController extends AbstractController {


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        System.out.println("hello");
        return new ModelAndView("welcome");
    }
}

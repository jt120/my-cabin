package com.jt.web.controller;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ze.liu on 2014/7/2.
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        Map map = req.getParameterMap();
        System.out.println(ToStringBuilder.reflectionToString(map, ToStringStyle.SHORT_PREFIX_STYLE));
    }
}

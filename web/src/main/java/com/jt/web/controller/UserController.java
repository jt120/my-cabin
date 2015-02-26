package com.jt.web.controller;

import com.jt.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author ze.liu
 * @since 2014/5/28
 */

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        logger.info("hello");
        String name = request.getParameter("name");
        System.out.println(name);
        System.out.println("hello out");
        return "hello";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response) {

        String ip = request.getLocalAddr();

        String ip2 = request.getHeader("X-Real-IP");
        response.addCookie(new Cookie("name","zhangsan"));
        System.out.println(ip + " : " + ip2);
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@Validated User user, BindingResult bindingResult) {

        System.out.println(user);
        return user.toString();
    }

    @RequestMapping(value = "addr", method = RequestMethod.POST)
    @ResponseBody
    public String addRequest(HttpServletRequest request) {
        User u = parseRequest(request);
        return u.toString();
    }

    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User u = new User(name,password);
        System.out.println(u);
        return u.toString();
    }


    @RequestMapping(value = "/rp")
    @ResponseBody
    public String rp(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("name", "张三"));
        return "rp";
    }

    private User parseRequest(HttpServletRequest request) {
        User u = new User();
        Map<String,String[]> params = request.getParameterMap();
        for(Map.Entry<String,String[]> item:params.entrySet()) {
            String key = item.getKey();
            String methodName = "set" + key.substring(0,1).toUpperCase() + key.substring(1);
            System.out.println("method name:" + methodName);
            Method setMethod = null;
            try {
                setMethod = User.class.getMethod(methodName, User.class, Object.class);
                String[] values = item.getValue();
                setMethod.invoke(u,values[0]);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return u;
    }
}

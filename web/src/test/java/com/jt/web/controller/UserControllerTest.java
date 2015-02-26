package com.jt.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ze.liu on 2014/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:hello-servlet.xml"})
public class UserControllerTest {
    @Autowired
    private UserController userController;
    //@Autowired
    //private RequestMappingHandlerAdapter handlerAdapter;

    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();

    @Test
    public void test01() throws Exception {
        request.setRequestURI("/hello");
        request.setMethod(HttpMethod.GET.name());
        request.setParameter("name", "zhangsan");
        //ModelAndView modelAndView = handlerAdapter.handle(request, response, new HandlerMethod(userController, "hello", HttpRequest.class));
        //System.out.println(modelAndView.getViewName());
    }
}

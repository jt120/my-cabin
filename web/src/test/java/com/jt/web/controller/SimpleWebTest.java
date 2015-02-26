package com.jt.web.controller;

import com.jt.web.simple.SimpleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.support.BindingAwareModelMap;

/**
 * Created by ze.liu on 2014/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:simple-web.xml"})
public class SimpleWebTest {
    @Autowired
    private SimpleController simpleController;
    //@Autowired
    //private RequestMappingHandlerAdapter handlerAdapter;

    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();

    @Test
    public void test01() {
        request.setParameter("name", "zhangsan");
        BindingAwareModelMap model = new BindingAwareModelMap();
        simpleController.hello(request, model);
        System.out.println("=============" + model.get("stu"));
    }
}

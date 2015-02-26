package com.jt.cabin.util;

import com.jt.cabin.entity.User;
import com.jt.cabin.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ze.liu
 * @since 2014/5/14
 */
public class TestSpringUtil {

    @Test
    public void test01() {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        UserService us = (UserService) SpringUtil.getCtx().getBean("userService");
        us.add(new User());
    }
}

package com.jt.boot;

import com.jt.bean.User;
import com.jt.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.StandardEnvironment;

import java.util.Iterator;

/**
 * @author ze.liu
 * @since 2014/5/15
 */
public class TestBoot {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

    @Test
    public void test01() {

        BootClass b = (BootClass) ctx.getBean("bootClass");
        b.say();
    }

    @Test
    public void test02() {
        UserServiceImpl us = (UserServiceImpl) ctx.getBean("userService");
        us.add(new User());
    }

    @Test
    public void test() {
    }

    @Test
    public void test03() {
        Environment environment = ctx.getEnvironment();
        String[] s = environment.getActiveProfiles();
        StandardEnvironment standardEnvironment = (StandardEnvironment) environment;
        PropertySources propertySources = standardEnvironment.getPropertySources();
        Iterator iterator = propertySources.iterator();
        while (iterator.hasNext()) {
            MapPropertySource mapPropertySource  = (MapPropertySource) iterator.next();
            String[] vs = mapPropertySource.getPropertyNames();
            for(String a:vs) {
                System.out.println(a+":"+mapPropertySource.getProperty(a));
            }
        }

        System.out.println(environment.getProperty("name"));
    }

    @Test
    public void test04() {
        User user = (User) ctx.getBean("user");
        System.out.println(user);
    }

    @Test
    public void test05() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = ctx.getBean(PropertyPlaceholderConfigurer.class);
        System.out.println(propertyPlaceholderConfigurer);
    }
}

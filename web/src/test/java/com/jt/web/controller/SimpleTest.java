package com.jt.web.controller;

import com.jt.web.model.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ze.liu on 2014/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-servlet.xml"})
public class SimpleTest {
    @Autowired
    Group group;
    @Test
    public void test() {
        System.out.println(group.getName());
    }
}

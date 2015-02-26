package com.jt.bean;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ze.liu on 2014/6/30.
 */
public class BeanTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        User u = new User();
        Method m = BeanUtils.findMethodWithMinimalParameters(User.class, "setName");
        String name = "hehe";
        m.invoke(u, new Object[]{name});
        System.out.println(u);
    }
}

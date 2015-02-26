package com.jt.service;

import com.jt.dao.UserDaoImpl;
import com.jt.util.SpringUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class OtherService {

    private static UserDaoImpl userDao;

    private OtherService() {
    }

    private static OtherService otherService = new OtherService();

    public static OtherService getInstance() {
        if (userDao == null) {
            System.out.println("init userDao");
            userDao = (UserDaoImpl) SpringUtil.getCtx().getBean("userDao");
        }
        return otherService;
    }


    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        applicationContext.start();

        OtherService otherService1 = getInstance();

        Thread.sleep(2000);

        OtherService otherService2 = getInstance();
    }
}

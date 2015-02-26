package com.jt.cabin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ze.liu
 * @since 2014/5/14
 */
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public static ApplicationContext getCtx() {
        return ctx;
    }
}

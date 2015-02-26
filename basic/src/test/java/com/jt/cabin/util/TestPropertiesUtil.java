package com.jt.cabin.util;

import org.junit.Test;

/**
 * @author ze.liu
 * @since 2014/5/14
 */
public class TestPropertiesUtil {

    @Test
    public void test() {
        String name = PropertiesUtil.getString("name","none");
        System.out.println(name);
    }

    @Test
    public void test02() {
        System.out.println(PropertiesUtil.getString("interest","none"));
        System.out.println(PropertiesUtil.getString("class", "12"));
    }
}

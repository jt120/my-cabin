package com.jt.cabin.util;

import org.apache.commons.lang.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author ze.liu
 * @since 2014/5/14
 */
public class PropertiesUtil {

    private static ResourceBundle rb;

    static {
        rb = ResourceBundle.getBundle("sys", Locale.SIMPLIFIED_CHINESE);
    }

    public static String getString(String key, String def) {
        String value = null;
        try {
            value = rb.getString(key);
        } catch (Exception e) {
        }
//        if(StringUtils.isBlank(value)) {
        if (value == null) {
            value = def;
        }
        return StringUtils.trim(value);
    }
}

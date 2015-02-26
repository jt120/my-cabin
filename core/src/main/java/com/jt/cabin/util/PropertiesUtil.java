package com.jt.cabin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * since 2015/2/26.
 */
public class PropertiesUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    public static Properties load(String name) {
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
            properties.load(resourceAsStream);
        } catch (IOException e) {
            log.warn("load properties error ", e);
        }
        return properties;
    }
}

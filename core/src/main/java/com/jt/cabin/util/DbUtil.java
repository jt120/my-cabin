package com.jt.cabin.util;

import dao.SimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ze.liu
 * @since 2014/6/11
 */
public class DbUtil {

    static Properties load = PropertiesUtil.load("db.properties");
    static String url = load.getProperty("url");
    static String username = load.getProperty("username");
    static String password = load.getProperty("password");

    public static Connection getConn() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static DataSource getDataSource() {
        return new SimpleDataSource();
    }


}

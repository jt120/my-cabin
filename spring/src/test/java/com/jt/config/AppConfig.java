package com.jt.config;

import com.jt.cabin.util.DbUtil;
import org.apache.commons.dbutils.DbUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * since 2015/2/26.
 */
@Configuration
@ComponentScan(value = "com.jt")
public class AppConfig {

    static {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        Resource s = new ClassPathResource("db-script.sql");
        Resource[] scripts = {s};
        populator.setScripts(scripts);
        Connection conn = DbUtil.getConn();

        try {
            populator.populate(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("go");
    }
}

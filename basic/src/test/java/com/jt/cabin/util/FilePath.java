package com.jt.cabin.util;


import java.io.File;
import java.util.Locale;

/**
 * @author ze.liu
 * @since 2014/5/14
 */
public class FilePath {

    public static void main(String[] args) {
        System.out.println(FilePath.class.getResource(""));
        System.out.println(FilePath.class.getResource("/"));
        System.out.println(FilePath.class.getClassLoader().getResource(""));
        System.out.println(FilePath.class.getClassLoader().getResource("/"));

        System.out.println("==================");

        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));

        System.out.println("==================");

        System.out.println(new File("").getAbsolutePath());
        System.out.println(new File("/").getAbsolutePath());

        Locale locale = Locale.getDefault();
        System.out.println(locale.getDisplayName() + ":" + locale.getLanguage() + ":" + locale.getCountry());
    }
}

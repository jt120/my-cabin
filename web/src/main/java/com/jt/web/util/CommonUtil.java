package com.jt.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

    /**
     * 获取ip
     * 
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     */
    public static String getGlobalCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("QunarGlobal".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || name == null) {
            return null;
        }

        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                return c.getValue();
            }
        }

        return null;
    }

}

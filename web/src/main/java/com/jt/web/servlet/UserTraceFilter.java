package com.jt.web.servlet;

import com.jt.web.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class UserTraceFilter implements Filter {

    public static final String COOKIE_NAME = "QN29";
    private static final Logger logger = LoggerFactory.getLogger(UserTraceFilter.class);

    @Override
    public void destroy() {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;

        String uuid = CommonUtil.getCookie(req, COOKIE_NAME);
        if (uuid == null || uuid.length() != 32) {
            uuid = UUID.randomUUID().toString().replaceAll("-", "");
            Cookie cookie = new Cookie(COOKIE_NAME, uuid);
            cookie.setPath("/");
            cookie.setMaxAge(10 * 365 * 24 * 60 * 60);
            ((HttpServletResponse) resp).addCookie(cookie);
        }

        MDC.put("traceid", uuid);

        try {
            chain.doFilter(request, resp);
        } catch (IOException e) {
            logError(e);
            throw e;
        } catch (ServletException e) {
            logError(e);
            throw e;
        } catch (RuntimeException e) {
            logError(e);
            throw e;
        } finally {
            MDC.remove("traceid");
        }
    }


    private void logError(Exception e) {
        logger.error("Request Error", e);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }
}

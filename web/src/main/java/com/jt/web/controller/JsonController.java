package com.jt.web.controller;

import com.jt.web.model.User;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ze.liu
 * @since 2014/5/29
 */
@Controller
@RequestMapping("/json")
public class JsonController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request) {

        String name = request.getParameter("name");
        System.out.println(name);
        return "hello";
    }

    @RequestMapping("/show")
    @ResponseBody
    public String list() throws IOException {
        User u = new User("admin", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "1");
        User user = new User("admin", "123");
        map.put("user", user);
        return objectMapper.writeValueAsString(map);
    }

    @RequestMapping("/{name}/add")
    @ResponseBody
    public String param(@PathVariable String name) throws IOException {
        User u = new User("admin", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "1");
        User user = new User("admin", "123");
        map.put("user", user);
        map.put("param", name);
        return objectMapper.writeValueAsString(map);
    }

    @RequestMapping("/test")
    public String test() {
        return "user/test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String test(HttpServletRequest req) {
        Map map = req.getParameterMap();
        Set set = map.entrySet();
        for (Object o : set) {
            System.out.println(o);
        }
        return ToStringBuilder.reflectionToString(map, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

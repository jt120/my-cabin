package com.jt.controller;

import com.jt.bean.User;
import com.jt.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * since 2015/2/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class HomeControllerTest {

    @Autowired
    private HomeController homeController;
    @Test
    public void testIndex01() throws Exception {
        MockMvc mockMvc = standaloneSetup(homeController).build();
        mockMvc.perform(get("/index")).andExpect(redirectedUrl("/user/login"));
    }

    @Test
    public void testIndex02() throws Exception {
        MockMvc mockMvc = standaloneSetup(homeController).build();
        User user = new User();
        user.setName("hello");
        user.setPassword("123");
        mockMvc.perform(get("/index").sessionAttr("loginUser",user))
                .andExpect(view().name("home"));
    }
}

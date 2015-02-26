package com.jt.controller;

import com.jt.bean.User;
import com.jt.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

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
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void test00() throws Exception {
        System.out.println(userController);
    }

    @Test
    public void testReg01() throws Exception {
        MockMvc mockMvc = standaloneSetup(userController).build();
        mockMvc.perform(get("/user/register")).andExpect(view().name("user/register"));
    }

    @Test
    public void testReg02() throws Exception {
        MockMvc mockMvc = standaloneSetup(userController).build();
        User user = new User();
        user.setName("hello");
        user.setPassword("123");
        mockMvc.perform(post("/user/register").param("name", "hello")
                .param("password", "123")).andExpect(view().name("user/register"))
                .andExpect(model().attribute("error", "用户已存在"));
    }

    @Test
    public void testReg03() throws Exception {
        //UserController userController = new UserController();
        MockMvc mockMvc = standaloneSetup(userController).build();
        User user = new User();
        user.setName("hello");
        user.setPassword("123");
        mockMvc.perform(post("/user/register").param("name", "hello123")
                .param("password", "1234567890123456789012345678901234567890")).andExpect(view().name("user/register"))
                .andExpect(model().attribute("error", "系统错误"));
    }

    @Test
    public void testReg04() throws Exception {
        MockMvc mockMvc = standaloneSetup(userController).build();
        User user = new User();
        user.setName("hello");
        user.setPassword("123");
        mockMvc.perform(post("/user/register").param("name", "hello123")
                .param("password", "1234")).andExpect(view().name("index"));
    }

    @Test
    public void testLogin01() throws Exception {
        MockMvc mockMvc = standaloneSetup(userController).build();
        mockMvc.perform(get("/user/login")).andExpect(view().name("user/login"));
    }

    @Test
    public void testLogin02() throws Exception {
        MockMvc mockMvc = standaloneSetup(userController).build();
        mockMvc.perform(post("/user/login").param("name", "hello")
                .param("password", "123")).andExpect(redirectedUrl("/index"));
    }

    @Test
    public void testLogin03() throws Exception {
        MockMvc mockMvc = standaloneSetup(userController).build();
        mockMvc.perform(post("/user/login").param("name", "hello")
                .param("password", "1234")).andExpect(view().name("user/login"))
                .andExpect(model().attribute("error", "用户名或密码错误"));
    }
}

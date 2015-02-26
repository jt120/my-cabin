package com.jt.web.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by ze.liu on 2014/8/25.
 */
@Controller
@RequestMapping("/valid")
public class ValidController {

    private static final Logger logger = LoggerFactory.getLogger(ValidController.class);

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "error";
        }
        return user.toString();

    }

    @RequestMapping(value = "add")
    @ResponseBody
    public String addGet(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn(bindingResult.getGlobalError().getDefaultMessage());
            logger.warn("ok");
            return "error";
        }
        return user.toString();

    }


    static class User {

        @NotBlank(message = "name不能为空")
        private String name;

        @Min(value = 1)
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}

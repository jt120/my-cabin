package com.jt.web.controller;

import com.jt.web.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ze.liu on 2014/7/25.
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @RequestMapping("/show")
    public void show(@RequestBody Member member) {
        System.out.println(member);
    }
}

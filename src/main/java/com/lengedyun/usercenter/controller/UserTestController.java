package com.lengedyun.usercenter.controller;

import com.lengedyun.usercenter.domain.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: UserTestController
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/10/8 21:34
 */
@RestController
@RequestMapping("userTest")
public class UserTestController {

    @GetMapping("getReqMuiltArgs")
    public User getReqMuiltArgs(User user){
        return user;
    }
}

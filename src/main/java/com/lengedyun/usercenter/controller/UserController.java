package com.lengedyun.usercenter.controller;

import com.lengedyun.usercenter.dao.user.UserMapper;
import com.lengedyun.usercenter.domain.entity.User;
import com.lengedyun.usercenter.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @title: UserController
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/9/23 8:02
 */

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        System.out.println("8071我被调用了");
        return userService.findUserById(id);
    }
}

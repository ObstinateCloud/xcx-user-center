package com.lengedyun.usercenter.controller;

import com.lengedyun.usercenter.domain.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope //nacos管理的配置属性动态刷新
public class UserTestController {

    @GetMapping("getReqMuiltArgs")
    public User getReqMuiltArgs(User user){
        return user;
    }

    @Value("${user.config}")
    private String userConfig;

    @Value("${common.config1}")
    private String commonConfig1;

    @Value("${common.config2}")
    private String commonConfig2;

    @Value("${common.config3}")
    private String commonConfig3;

    @Value("${common.config4}")
    private String commonConfig4;

    @GetMapping("getNacosConfig")
    public String getNaocsConfig(){
        return this.commonConfig1+","+commonConfig2+","+this.userConfig+","+commonConfig3+","+commonConfig4;
    }
}

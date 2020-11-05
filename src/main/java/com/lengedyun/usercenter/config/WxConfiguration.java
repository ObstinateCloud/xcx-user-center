package com.lengedyun.usercenter.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: WxConfiguration
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/11/5 7:18
 */
@Configuration
public class WxConfiguration {

    //微信miniapp即微信小程序
    @Bean
    public WxMaConfig wxMaConfig(){

        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid("wxda55ed43dafd41b2");
        config.setSecret("4947ab3c8eb9fdce009fdca1a04c5afe");
        return config;
    }

    @Bean
    public WxMaService wxMaService(WxMaConfig wxMaConfig){
        WxMaServiceImpl wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxMaConfig);
        return wxMaService;
    }
}

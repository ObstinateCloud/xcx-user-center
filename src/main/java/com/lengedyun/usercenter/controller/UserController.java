package com.lengedyun.usercenter.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.lengedyun.usercenter.dao.user.UserMapper;
import com.lengedyun.usercenter.domain.dto.user.JwtTokenRespDTO;
import com.lengedyun.usercenter.domain.dto.user.LoginRespDTO;
import com.lengedyun.usercenter.domain.dto.user.UserLoginDTO;
import com.lengedyun.usercenter.domain.dto.user.UserRespDTO;
import com.lengedyun.usercenter.domain.entity.User;
import com.lengedyun.usercenter.service.user.UserService;
import com.lengedyun.usercenter.utils.JwtOperator;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @title: UserController
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/9/23 8:02
 */

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private JwtOperator jwtOperator;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        System.out.println("8071我被调用了");
        return userService.findUserById(id);
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO userLoginDTO) throws WxErrorException {
          // 微信小程序服务端校验是否已经登录
        WxMaJscode2SessionResult sessionInfo = this.wxMaService.getUserService()
                .getSessionInfo(userLoginDTO.getCode());
        //获取用户返回的唯一标识
        String openid = sessionInfo.getOpenid();
        //如果未登录会抛异常

        //查看用户在用户中心是否注册
        User loginUser = this.userService.login(userLoginDTO, openid);
        //颁发token
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id",loginUser.getId());
        userInfo.put("wxNickname",loginUser.getWxNickname());
        userInfo.put("role",loginUser.getRoles());
        String token = jwtOperator.generateToken(userInfo);
        log.info(
                "用户{}登录成功，生成token={}，有效期到{}",
                loginUser.getWxNickname(),
                token,
                jwtOperator.getExpirationDateFromToken(token)
        );
        //构建响应
        return LoginRespDTO.builder()
                .token(
                        JwtTokenRespDTO.builder()
                                .token(token)
                                .expirationTime(jwtOperator.getExpirationDateFromToken(token).getTime())
                                .build()
                )
                .user(UserRespDTO.builder()
                                .id(loginUser.getId())
                                .wxNickname(loginUser.getWxNickname())
                                .avatarUrl(loginUser.getAvatarUrl())
                                .bonus(loginUser.getBonus())
                                .build())
                .build();
    }
}

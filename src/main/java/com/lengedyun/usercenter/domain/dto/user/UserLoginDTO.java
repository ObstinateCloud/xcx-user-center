package com.lengedyun.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: UserLoginDTO
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/11/5 7:12
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO {
    //wx.login方法返回结果
    private String code;

    private String wxNickname;


    private String avatarUrl;
}

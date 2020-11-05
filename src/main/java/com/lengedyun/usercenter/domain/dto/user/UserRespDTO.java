package com.lengedyun.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: UserRespDTO
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/11/5 7:03
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRespDTO {

    private Integer id;

    private String wxNickname;

    private Integer bonus;

    private String avatarUrl;
}



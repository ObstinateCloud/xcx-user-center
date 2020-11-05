package com.lengedyun.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: LoginDTO
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/11/5 7:05
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespDTO {

    private JwtTokenRespDTO token;

    private UserRespDTO user;
}

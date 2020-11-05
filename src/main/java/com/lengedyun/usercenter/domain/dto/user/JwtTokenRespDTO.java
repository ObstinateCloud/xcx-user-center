package com.lengedyun.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: JwtTokenRespDTO
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/11/5 7:01
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtTokenRespDTO {

    private String token;

    /**
     * 过期时间
     */
    private Long expirationTime;
}

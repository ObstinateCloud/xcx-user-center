package com.lengedyun.usercenter.service.user;

import com.lengedyun.usercenter.dao.user.UserMapper;
import com.lengedyun.usercenter.domain.dto.user.UserLoginDTO;
import com.lengedyun.usercenter.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @title: UserService
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/9/24 7:06
 */

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User login(UserLoginDTO userLoginDTO,String openId){
        User user = userMapper.selectOne(
                User.builder()
                        .wxId(openId)
                        .build()
        );
        if(user ==null){
            //未注册
            User userToSave = User.builder()
                    .wxId(openId)
                    .avatarUrl(userLoginDTO.getAvatarUrl())
                    .wxNickname(userLoginDTO.getWxNickname())
                    .bonus(300)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .roles("user")
                    .build();
            int i = userMapper.insertSelective(userToSave);
            return userToSave;
        }
        return user;

    }
}

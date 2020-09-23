package com.lengedyun.usercenter.service.user;

import com.lengedyun.usercenter.dao.user.UserMapper;
import com.lengedyun.usercenter.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

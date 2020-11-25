package com.lengedyun.usercenter.service.user;

import com.lengedyun.usercenter.dao.log.BonusEventLogMapper;
import com.lengedyun.usercenter.dao.user.UserMapper;
import com.lengedyun.usercenter.domain.dto.messaging.UserAddBonusMsgDto;
import com.lengedyun.usercenter.domain.dto.user.UserAddBonusDto;
import com.lengedyun.usercenter.domain.dto.user.UserLoginDTO;
import com.lengedyun.usercenter.domain.entity.BonusEventLog;
import com.lengedyun.usercenter.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BonusEventLogMapper bonusEventLogMapper;

    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User login(UserLoginDTO userLoginDTO, String openId) {
        User user = userMapper.selectOne(
                User.builder()
                        .wxId(openId)
                        .build()
        );
        if (user == null) {
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

    public Integer addUserBonus(UserAddBonusDto userAddBonusDto) {
        User user = this.userMapper.selectByPrimaryKey(userAddBonusDto.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }

//        修改积分
        Integer result = this.userMapper.updateByPrimaryKeySelective(
                User.builder()
                        .id(user.getId())
                        .bonus(user.getBonus() + userAddBonusDto.getBonus())
                        .updateTime(new Date())
                        .build()
        );
        //2.记录日志
        bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userAddBonusDto.getUserId())
                        .value(userAddBonusDto.getBonus())
                        .event(userAddBonusDto.getEvent())
                        .createTime(new Date())
                        .description(userAddBonusDto.getDescription())
                        .build()
        );
        log.info("积分添加完成");

        return result;
    }
}

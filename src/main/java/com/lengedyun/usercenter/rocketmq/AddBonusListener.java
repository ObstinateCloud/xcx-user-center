package com.lengedyun.usercenter.rocketmq;

import com.lengedyun.usercenter.dao.log.BonusEventLogMapper;
import com.lengedyun.usercenter.dao.user.UserMapper;
import com.lengedyun.usercenter.domain.dto.messaging.UserAddBonusMsgDto;
import com.lengedyun.usercenter.domain.entity.BonusEventLog;
import com.lengedyun.usercenter.domain.entity.User;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @title: AddBonusListener
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/10/18 21:53
 */

//@Component
//@RocketMQMessageListener(topic = "add-bonus",consumerGroup = "consumer-group")
@Slf4j
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDto> {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDto userAddBonusMsgDto) {
        //收到消息
        //1.为用户加积分
        Integer userId = userAddBonusMsgDto.getUserId();
        Integer bouns = userAddBonusMsgDto.getBonus();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus()+bouns);

        this.userMapper.updateByPrimaryKeySelective(user);
        //2.记录日志
        bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bouns)
                        .event("contribute")
                        .createTime(new Date())
                        .description("投稿加积分")
                        .build()
        );
        log.info("积分添加完成");
    }
}

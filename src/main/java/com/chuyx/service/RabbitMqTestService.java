package com.chuyx.service;

import org.springframework.stereotype.Service;

/**
 * @author yuxiang.chu
 * @date 2022/8/5 17:46
 **/
public interface RabbitMqTestService {

    void sendSimpleMessage(String queueMessage);
}

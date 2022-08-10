package com.chuyx.service.impl;

import com.chuyx.service.RabbitMqTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuxiang.chu
 * @date 2022/8/5 17:47
 **/
@Service
@Slf4j
public class RabbitMqTestServiceImpl implements RabbitMqTestService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendSimpleMessage(String queueMessage) {
        rabbitTemplate.send("chuyx.direct.queue", new Message(queueMessage.getBytes(), new MessageProperties()));
        rabbitTemplate.send("chuyx.fanout.myself.exchange",  new Message(queueMessage.getBytes(), new MessageProperties()));
    }
}

package com.chuyx.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yuxiang.chu
 * @date 2022/8/5 17:50
 **/
@Component
@Slf4j
public class TestListener {




    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "chuyx.direct.exchange", durable = "true"),
                    exchange = @Exchange(value = "chuyx.direct.exchange")),
            concurrency = "1"
    )
    public void handle(Message message, Channel channel){
        String body = new String(message.getBody());
        try {
            log.info("[消费]-{}", body);
        } catch (Exception e) {
            //catch异常，避免异常消息循环消费。打印日志，确认失败消息。
            log.error("已分派已量房监控，消费失败, 消息体：{}, 错误：{}", body, e);
        }
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "chuyx.fanout.myself.queue.one", durable = "true"),
                    exchange = @Exchange(value = "chuyx.fanout.myself.exchange")),
            concurrency = "1"
    )
    public void handleFanout1(Message message, Channel channel) throws Exception {
        String body = new String(message.getBody());
        int a = 1/Integer.parseInt(body);
        log.info("[广播-消费1]-{}", body);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "chuyx.fanout.myself.queue.two", durable = "true"),
                    exchange = @Exchange(value = "chuyx.fanout.myself.exchange")),
            concurrency = "1"
    )
    public void handleFanout2(Message message, Channel channel) throws Exception {
        String body = new String(message.getBody());
        log.info("[广播-消费2]-{}", body);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "chuyx.fanout.myself.queue.three", durable = "true"),
                    exchange = @Exchange(value = "chuyx.fanout.myself.exchange")),
            concurrency = "1"
    )
    public void handleFanout3(Message message, Channel channel) throws Exception {
        String body = new String(message.getBody());
        log.info("[广播-消费3]-{}", body);
    }
}

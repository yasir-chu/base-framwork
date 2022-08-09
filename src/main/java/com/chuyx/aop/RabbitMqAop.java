package com.chuyx.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yuxiang.chu
 * @date 2022/8/9 11:35
 **/
@Component
@Aspect
@Slf4j
public class RabbitMqAop {


    @Pointcut("@annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)")
    public void listenerPointCut(){

    }

    @Around(value = "listenerPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        // 执行源方法
        Object[] args = joinPoint.getArgs();
        Message message = (Message) args[0];
        Channel channel = (Channel) args[1];
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("[Rabbitmq]-从队列-{}，接受到消息：{}", message.getMessageProperties().getConsumerQueue(), JSON.toJSONString(new String(message.getBody())));
        try {
            result = joinPoint.proceed();
            channel.basicAck(deliveryTag, true);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Signature signature = joinPoint.getSignature();
            String declaringTypeName = signature.getDeclaringTypeName();
            String name = signature.getName();
            channel.basicNack(deliveryTag, true, true);
            log.error("[Rabiitmq]-[消费失败]-请求方法：{}, e:{}", declaringTypeName + "#" + name, Throwables.getStackTraceAsString(throwable));
        }
        return result;
    }
}
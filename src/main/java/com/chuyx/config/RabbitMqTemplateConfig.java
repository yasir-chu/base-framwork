package com.chuyx.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuxiang.chu
 * @date 2022/8/5 17:24
 **/
@Configuration
@Slf4j
public class RabbitMqTemplateConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        // 消息发送确认机制 - 发送到交换机回调
        rabbitTemplate.setConfirmCallback(confirmCallback);
        // 消息发送确认机制 - 发送到队列回调   注：如果队列开启了持久化，这消息会保存到队列后回调
        rabbitTemplate.setReturnCallback(returnCallback);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();

    }

    /**
     * 高级特性-发送到交换机回调
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            assert correlationData != null;
            Message message = correlationData.getReturnedMessage();
            if (ack) {
                // 成功
                log.info("[发送mq成功]-{}", message);
            } else {
                // 失败
                log.info("[发送mq失败]-{}", correlationData.getId());
                // 进行重试
                if (correlationData.getReturnedMessage() == null) {
                    log.info("[rabbitmq]-[发送信息为空]-不重试，抛弃信息，记录日志-{}", JSON.toJSONString(message));
                    return;
                }
                Message returnedMessage = correlationData.getReturnedMessage();
                String routingKey = returnedMessage.getMessageProperties().getReceivedRoutingKey();
                String exchange = returnedMessage.getMessageProperties().getReceivedExchange();
                String queue = returnedMessage.getMessageProperties().getConsumerQueue();
                byte[] messageBody = message != null ? message.getBody() : null;
                log.info("[]-{},{},{},{}", routingKey, exchange, queue, messageBody);
            }
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {

        /**
         * @param message    消息信息  body属性是消息内容
         * @param replyCode  应答码
         * @param replyText  原因描述
         * @param exchange   交换机
         * @param routingKey 路由键
         */
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            log.info("[rabbitMQ]-[returnMessage]-消息：{},应答码：{}, 描述原因：{}，交换机：{}, 路由键：{}", JSON.toJSONString(message), replyCode, replyText, exchange, replyCode);
        }
    };

}

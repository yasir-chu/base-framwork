package com.chuyx.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * @author yuxiang.chu
 * @date 2022/8/5 17:55
 **/
public class RabbitConfig {

    /**
     * 广播
     */
    public static final String FANOUT_CHUYX_ONE_EXCHANGE = "chuyx.fanout.myself.exchange";

    /**
     * 队列名
     */
    public static final String FANOUT_CHUYX_QUEUE_ONE = "chuyx.fanout.myself.queue.one";
    public static final String FANOUT_CHUYX_QUEUE_TWO = "chuyx.fanout.myself.queue.two";
    public static final String FANOUT_CHUYX_QUEUE_THREE = "chuyx.fanout.myself.queue.three";


    @Bean("chuyxFanoutMyselfExchange")
    public FanoutExchange getFanoutOneExchange() {
        return new FanoutExchange(FANOUT_CHUYX_ONE_EXCHANGE);
    }

    @Bean("chuyxFanoutMyselfQueueOne")
    public Queue getFanoutQueueOne(){
        return new Queue(FANOUT_CHUYX_QUEUE_ONE);
    }

    @Bean("chuyxFanoutMyselfQueueTwo")
    public Queue getFanoutQueueTwo(){
        return new Queue(FANOUT_CHUYX_QUEUE_TWO);
    }

    @Bean("chuyxFanoutMyselfQueueThree")
    public Queue getFanoutQueueThree(){
        return new Queue(FANOUT_CHUYX_QUEUE_THREE);
    }

    @Bean("bindingFanoutOne")
    public Binding bindingFanoutOne(@Qualifier("chuyxFanoutMyselfQueueOne") Queue queue, @Qualifier("chuyxFanoutMyselfExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("chuyx.fanout.myself.exchange").noargs();
    }

    @Bean("bindingFanoutTwo")
    public Binding bindingFanoutTwo(@Qualifier("chuyxFanoutMyselfQueueTwo") Queue queue, @Qualifier("chuyxFanoutMyselfExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("chuyx.fanout.myself.exchange").noargs();
    }

    @Bean("bindingFanoutThree")
    public Binding bindingFanoutThree(@Qualifier("chuyxFanoutMyselfQueueThree") Queue queue, @Qualifier("chuyxFanoutMyselfExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("chuyx.fanout.myself.exchange").noargs();
    }

}

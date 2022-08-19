package com.chuyx.config;

import io.lyh.dtp.common.em.QueueTypeEnum;
import io.lyh.dtp.common.em.RejectedTypeEnum;
import io.lyh.dtp.core.DtpExecutor;
import io.lyh.dtp.core.ThreadPoolBuilder;
import io.lyh.dtp.support.DtpCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yuxiang.chu
 * @date 2022/8/19 10:48
 **/
@Configuration
public class DtpConfig {

    @Bean
    public DtpExecutor demoExecutor() {
        return DtpCreator.createDynamicFast("demo1-executor");
    }

    @Bean
    public ThreadPoolExecutor demoExecutor2() {
        return ThreadPoolBuilder.newBuilder()
                .threadPoolName("demo1-executor")
                .corePoolSize(8)
                .maximumPoolSize(16)
                .keepAliveTime(50)
                .allowCoreThreadTimeOut(true)
                .workQueue(QueueTypeEnum.SYNCHRONOUS_QUEUE.getName(), null, false)
                .rejectedExecutionHandler(RejectedTypeEnum.CALLER_RUNS_POLICY.getName())
                .buildDynamic();
    }
}

package com.chuyx.service.impl;

import com.chuyx.service.TestService;
import com.dtp.core.DtpRegistry;
import com.dtp.core.thread.DtpExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author chuyx
 * @date 2021/5/4 16:46
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {


    @Value("${test.value}")
    private String value;
    @Value("${test.test.test.value}")
    private String value2;
    @Value("${spring.dynamic.tp.executors[0].threadPoolName}")
    private String a;
    @Value("${spring.dynamic.tp.executors[0].queueType}")
    private String b;

    @Resource
    private ThreadPoolExecutor demoExecutor1;

    @Resource
    private ThreadPoolExecutor demoExecutor2;

    @Override
    public void testThread() {
//        DtpExecutor dtpExecutor = DtpRegistry.getExecutor("demo1-executor");
        System.out.println(value);
        System.out.println(value2);
        log.info("[]----{},{}", a, b);
        demoExecutor1.execute(() -> System.out.println("test"));


    }

    @Override
    public void getThreadPoolMessage() {
        DtpExecutor dtpExecutor = DtpRegistry.getDtpExecutor("demoExecutor1");
        BlockingQueue<Runnable> queue = dtpExecutor.getQueue();
        System.out.println(Thread.currentThread().getName() + "-" + dtpExecutor.getThreadPoolName() + ":-" +
                "核心线程数：" + dtpExecutor.getCorePoolSize() +
                "活动线程数：" + dtpExecutor.getActiveCount() +
                "最大线程数：" + dtpExecutor.getMaximumPoolSize() +
                "线程池活跃度：" +
                divide(dtpExecutor.getActiveCount(), dtpExecutor.getMaximumPoolSize()) +
                "任务完成数：" + dtpExecutor.getCompletedTaskCount() +
                "队列大小：" + (queue.size() + queue.remainingCapacity()) +
                "当前排队线程数" + queue.size() +
                "队列剩余大小" + queue.remainingCapacity() +
                "队列使用度" + divide(queue.size(), queue.size() + queue.remainingCapacity())
        );
        log.info("-------------------------------------------------------------------");
        BlockingQueue<Runnable> queue2 = demoExecutor1.getQueue();
        System.out.println(Thread.currentThread().getName() + //"-" + demoExecutor1.getThreadPoolName() + ":-" +
                "核心线程数：" + demoExecutor1.getCorePoolSize() +
                "活动线程数：" + demoExecutor1.getActiveCount() +
                "最大线程数：" + demoExecutor1.getMaximumPoolSize() +
                "线程池活跃度：" +
                divide(demoExecutor1.getActiveCount(), demoExecutor1.getMaximumPoolSize()) +
                "任务完成数：" + demoExecutor1.getCompletedTaskCount() +
                "队列大小：" + (queue2.size() + queue2.remainingCapacity()) +
                "当前排队线程数" + queue2.size() +
                "队列剩余大小" + queue2.remainingCapacity() +
                "队列使用度" + divide(queue2.size(), queue2.size() + queue2.remainingCapacity())
        );
        log.info("-------------------------------------------------------------------");
        BlockingQueue<Runnable> queue3 = demoExecutor2.getQueue();
        System.out.println(Thread.currentThread().getName() + //"-" + demoExecutor2.getThreadPoolName() + ":-" +
                "核心线程数：" + demoExecutor2.getCorePoolSize() +
                "活动线程数：" + demoExecutor2.getActiveCount() +
                "最大线程数：" + demoExecutor2.getMaximumPoolSize() +
                "线程池活跃度：" +
                divide(demoExecutor2.getActiveCount(), demoExecutor2.getMaximumPoolSize()) +
                "任务完成数：" + demoExecutor2.getCompletedTaskCount() +
                "队列大小：" + (queue3.size() + queue3.remainingCapacity()) +
                "当前排队线程数" + queue3.size() +
                "队列剩余大小" + queue3.remainingCapacity() +
                "队列使用度" + divide(queue3.size(), queue3.size() + queue3.remainingCapacity())
        );
    }

    @Override
    public void testPolicy() {
        for (int i = 0; i < 11; i++) {
            demoExecutor1.execute(() -> {
                log.info("[线程名]-{}：开始执行", Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    log.error("休眠失败");
                }
                log.info("[线程名]-{}：执行结束", Thread.currentThread().getName());
            });
        }
    }

    private static String divide(int num1, int nums2) {
        return String.format("%1.2f%%", Double.parseDouble(num1 + "") / Double.parseDouble(nums2 + "") * 100);
    }
}

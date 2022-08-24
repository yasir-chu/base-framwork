package com.chuyx.service;


/**
 * @author chuyx
 * @date 2021/5/4 16:46
 */
public interface TestService {

    /**
     * 线程池使用测试
     */
    void testThread();


    /**
     * 打印线程池信息
     */
    void getThreadPoolMessage();

    /**
     * 线程池使用拒绝策略测试
     */
    void testPolicy();
}

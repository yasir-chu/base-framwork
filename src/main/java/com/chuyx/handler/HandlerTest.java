package com.chuyx.handler;


import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * 测试xxl-job
 *
 * @author chuyx
 * @date 2021/5/6 14:45
 */
@Component
public class HandlerTest{


    /**
     * 测试自动任务
     * @param str 自动任务入参
     * @return 成功还是失败
     */
    @XxlJob(value = "testHandler")
    public ReturnT<String> testHandler(String str){
        System.out.println("=====hello world=====");
        return ReturnT.SUCCESS;
    }
}

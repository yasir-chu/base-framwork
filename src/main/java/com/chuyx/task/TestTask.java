package com.chuyx.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yuxiang.chu
 * @date 2022/8/11 16:14
 **/
@Component
@Slf4j
public class TestTask extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        log.info("[测试任务]------------------");
        return ReturnT.SUCCESS;
    }
}

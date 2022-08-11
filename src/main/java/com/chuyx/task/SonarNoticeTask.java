package com.chuyx.task;

import com.chuyx.service.SonarRobotService;
import com.chuyx.service.impl.SonarRobotServiceImpl;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuxiang.chu
 * @date 2022/8/11 11:46
 **/
@Component
@Slf4j
public class SonarNoticeTask extends IJobHandler {

    @Autowired
    private SonarRobotService sonarRobotService;

    @Override
    public ReturnT<String> execute(String s) {
        long startTime = System.currentTimeMillis();
        log.info("sonar质量阈通知任务开始: {}", startTime);
        try {
            sonarRobotService.findSonarQube();
        } catch (Exception e) {
            log.error("sonar质量阈通知任务出错", e);
            return ReturnT.FAIL;
        }
        long endTime = System.currentTimeMillis();
        log.info("sonar质量阈通知任务结束: {}，耗时{}", endTime, endTime - startTime);
        return ReturnT.SUCCESS;
    }
}

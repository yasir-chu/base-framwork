package com.chuyx.service.impl;

import com.chuyx.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yuxiang.chu
 * @date 2022/8/15 9:36
 **/
@Slf4j
@Service
public class TestServiceImpl implements TestService {


    @Override
    public void logTest(String logMessage) {
        log.info("[测试日志输出]-[打印]-{}", logMessage);
    }
}

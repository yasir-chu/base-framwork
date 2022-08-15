package com.chuyx.controller;

import com.chuyx.api.TestApi;
import com.chuyx.contant.CodeMsg;
import com.chuyx.exception.CommonException;
import com.chuyx.service.RabbitMqTestService;
import com.chuyx.service.SonarRobotService;
import com.chuyx.service.TestService;
import com.chuyx.service.impl.SonarRobotServiceImpl;
import com.chuyx.vo.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author yuxiang.chu
 * @date 2022/1/20 14:48
 **/
@Controller
public class TestController implements TestApi {

    @Autowired
    private SonarRobotService sonarRobotService;

    @Autowired
    private RabbitMqTestService rabbitMqTestService;

    @Autowired
    private TestService testService;

    @Override
    public ResultPage<String> exceptionTest() {
        throw new CommonException(new CodeMsg(10001, "参数错误"));
    }

    @Override
    public ResultPage<String> success() {
        return ResultPage.data(123456);
    }

    @Override
    public ResultPage<String> post() {
        sonarRobotService.findSonarQube();
        return ResultPage.data(1);
    }


    @Override
    public ResultPage<String> mqSendSimpleMessage(String message) {
        rabbitMqTestService.sendSimpleMessage(message);
        return ResultPage.data(1);
    }

    @Override
    public ResultPage<String> logTest(String message) {
        testService.logTest(message);
        return ResultPage.data(1);
    }
}

package com.chuyx.controller;

import com.chuyx.api.TestApi;
import com.chuyx.contant.CodeMsg;
import com.chuyx.exception.CommonException;
import com.chuyx.service.HttpRequestService;
import com.chuyx.service.RabbitMqTestService;
import com.chuyx.task.SonarRobot;
import com.chuyx.task.TestSetTag;
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
    private TestSetTag testSetTag;

    @Autowired
    private SonarRobot sonarRobot;

    @Override
    public ResultPage<String> exceptionTest() {
        testSetTag.test();
        throw new CommonException(new CodeMsg(10001, "参数错误"));
    }

    @Override
    public ResultPage<String> success() {
        return ResultPage.data("123456");
    }

    @Override
    public ResultPage<String> post() {
        SonarRobot.findSonarQube();
        return ResultPage.data("!");

    }


    @Autowired
    private RabbitMqTestService rabbitMqTestService;
    @Override
    public ResultPage<String> mqSendSimpleMessage(String message) {
        rabbitMqTestService.sendSimpleMessage(message);
        return ResultPage.data("1");
    }
}

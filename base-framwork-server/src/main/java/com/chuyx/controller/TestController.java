package com.chuyx.controller;

import com.chuyx.api.TestApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * @author yuxiang.chu
 * @date 2022/8/17 14:19
 **/
@Controller
public class TestController implements TestApi {

    @Value("${test.value}")
    private String value;

    @Override
    public void testApollo() {
        System.out.println("测试apollo配置" + value);
    }
}

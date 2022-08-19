package com.chuyx.controller;

import com.chuyx.api.TestApi;
import com.chuyx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author yuxiang.chu
 * @date 2022/8/19 10:44
 **/
@Service
@Controller
public class TestController implements TestApi {

    @Autowired
    private TestService testService;


    @Override
    public String test() {
        testService.testThread();
        return null;
    }
}

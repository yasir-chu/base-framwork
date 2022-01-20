package com.chuyx.controller;

import com.alibaba.fastjson.JSONObject;
import com.chuyx.api.TestApi;
import com.chuyx.contant.CodeMsg;
import com.chuyx.exception.CommonException;
import com.chuyx.service.HttpRequestService;
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
    private HttpRequestService httpRequestService;

    @Override
    public ResultPage<String> exceptionTest() {
        throw new CommonException(new CodeMsg(10001, "参数错误"));
    }

    @Override
    public ResultPage<String> success() {
        return ResultPage.data("123456");
    }

    @Override
    public ResultPage<String> post() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msgtype","text");
        JSONObject text = new JSONObject();
        text.put("content","测试");
        jsonObject.put("text", text);
        JSONObject post = httpRequestService.post("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=9236a70f-8058-4c3b-9558-e7824c346d18", jsonObject);
        return ResultPage.data(post.toString());

    }
}

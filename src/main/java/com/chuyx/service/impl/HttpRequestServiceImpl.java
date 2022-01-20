package com.chuyx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chuyx.service.HttpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuxiang.chu
 * @date 2022/1/20 14:11
 **/
public class HttpRequestServiceImpl implements HttpRequestService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject post(String url, JSONObject requestJson) {
//        restTemplate.
        return null;
    }
}

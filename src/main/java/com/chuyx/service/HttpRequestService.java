package com.chuyx.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuxiang.chu
 * @date 2022/1/20 14:04
 **/
@Component
public interface HttpRequestService {

    JSONObject post(String url, JSONObject requestJson);

}

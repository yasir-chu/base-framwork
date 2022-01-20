package com.chuyx.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuxiang.chu
 * @date 2022/1/20 14:04
 **/
public interface HttpRequestService {

    /**
     * post 请求
     * @param url 请求url
     * @param requestJson 请求参数
     * @return 请求结果
     */
    JSONObject post(String url, JSONObject requestJson);

    /**
     * get 请求
     * @param url 请求url
     * @return 请求结果
     */
    JSONObject get(String url);

}

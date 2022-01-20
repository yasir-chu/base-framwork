package com.chuyx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chuyx.contant.CodeMsg;
import com.chuyx.contant.NormalConstant;
import com.chuyx.exception.CommonException;
import com.chuyx.service.HttpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author yuxiang.chu
 * @date 2022/1/20 14:11
 **/
@Service
public class HttpRequestServiceImpl implements HttpRequestService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject post(String url, JSONObject requestJson) {
        if (StringUtils.isEmpty(url)) {
            throw new CommonException(new CodeMsg(10001, "请求URL不可为空"));
        }
        HttpEntity<String> httpEntity = getHttpEntity(requestJson);
        ResponseEntity<String> result = restTemplate.postForEntity(url, httpEntity, String.class);
        if (result.getStatusCodeValue() != NormalConstant.SUCCESS_CODE) {
            throw new CommonException(new CodeMsg(10005, "请求：" + url + "失败：" + result.getStatusCode()));
        }
        return JSON.parseObject(result.getBody());
    }

    @Override
    public JSONObject get(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new CommonException(new CodeMsg(10001, "请求URL不可为空"));
        }
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        if (result.getStatusCodeValue() != NormalConstant.SUCCESS_CODE) {
            throw new CommonException(new CodeMsg(10005, "请求：" + url + "失败：" + result.getStatusCode()));
        }
        return JSON.parseObject(result.getBody());
    }

    /**
     * 获取HttpEntity
     *
     * @param requestJson 请求参数
     * @return 结果
     */
    private HttpEntity<String> getHttpEntity(JSONObject requestJson) {
        return new HttpEntity<>(requestJson.toString(), getHttpHeaders());
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        return httpHeaders;
    }
}

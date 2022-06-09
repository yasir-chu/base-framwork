package com.chuyx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;

@Component
@Slf4j
public class RestTemplateUtil {

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject sendPostRequest(String url, JSONObject json) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return sendPostRequest(url, json, headers);
    }

    public JSONObject sendPostRequest(String url, JSONObject json, HttpHeaders headers) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        HttpEntity<String> formEntity = new HttpEntity<>(json.toString(), headers);
        String result = restTemplate.postForEntity(url, formEntity, String.class).getBody();
        if (StringUtils.isBlank(result)) {
            return null;
        }
        return JSONObject.parseObject(result);
    }

    public void sendPostRequestFromString(String url, List<String> cookies, String key, String tag) {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        LinkedMultiValueMap<Object, Object> body = new LinkedMultiValueMap<>();
        body.add("issue", key);
        body.add("tags", tag);
        HttpEntity<LinkedMultiValueMap<Object, Object>> entity = new HttpEntity<>(body, httpHeaders);
        restTemplate.postForEntity(url, entity, String.class);
    }

    public JSONObject sendGetRequest(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        String result = restTemplate.getForEntity(url, String.class).getBody();
        if (StringUtils.isBlank(result)) {
            return null;
        }
        return JSONObject.parseObject(result);
    }

    public List<String> sendPostRequestLogin(String url, JSONObject login) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        // 设置请求boby
        HttpEntity<String> entity = new HttpEntity<>(login.toString(), headers);

        ResponseEntity<String> resp = restTemplate.postForEntity(url, entity, String.class);
        return resp.getHeaders().get("Set-Cookie");
    }

    public List<String> login(String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        LinkedMultiValueMap<Object, Object> body = new LinkedMultiValueMap<>();
        body.add("login", "yuxiang.chu");
        body.add("password", "yuxiang.chu@corp.to8to.com");
        HttpEntity<LinkedMultiValueMap<Object, Object>> entity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, entity, String.class);
        List<String> cookies = stringResponseEntity.getHeaders().get("Set-Cookie");
        log.info(cookies.toString());
        return cookies;
    }
}

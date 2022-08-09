package com.chuyx.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuyx.pojo.TagKeyValueDTO;
import com.chuyx.util.HttpUtil;
import com.chuyx.util.RestTemplateUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yuxiang.chu
 * @date 2022/4/15 17:39
 **/
@Component
public class TestSetTag {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    public void setTagNull() {
        List<String> cookies = restTemplateUtil.login("http://10.50.1.121:9000/api/authentication/login");


        String url = "http://10.50.1.121:9000/api/issues/search?componentKeys=t8t.mid%3At8t-mid-uc&s=FILE_LINE&resolved=false&tags=api-design&ps=100&facets=severities%2Ctypes%2Ctags%2CfileUuids&additionalFields=_all";
        JSONObject jsonObject = HttpUtil.get(url);
        JSONArray issues = jsonObject.getJSONArray("issues");
        for (Object issue : issues) {
            JSONObject json = (JSONObject) issue;
            String key = json.getString("key");
            String url2 = "http://10.50.1.121:9000/api/issues/set_tags";
            restTemplateUtil.sendPostRequestFromString(url2, cookies, key, null);
        }
    }


    public void login() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", "yuxiang.chu");
        jsonObject.put("password", "yuxiang.chu@corp.to8to.com");
        String url = "http://10.50.1.121:9000/api/authentication/login";
        String post = HttpUtil.post(url, jsonObject.toString());
    }

    public void test() {
        ArrayList<TagKeyValueDTO> tagKeyValueDTOS = new ArrayList<>();
        TagKeyValueDTO tagKeyValueDTO = new TagKeyValueDTO();
        tagKeyValueDTO.setKey("!");
        tagKeyValueDTO.setValue("2");
        tagKeyValueDTOS.add(tagKeyValueDTO);
        String s = JSONObject.toJSONString(tagKeyValueDTOS);
    }
}

package com.chuyx.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;


public class HttpUtil {
    /**
     * 发送 get 请求
     *
     * @throws Exception
     * @return
     */
    public static JSONObject get(String url) {
 
        String result = "";
        InputStream in = null;
        try {
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) new URL(url)
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Cookie", "XSRF-TOKEN=j6d8oe8f7tbrv589dmg2fgn2gj; JWT-SESSION=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJBWUQtRVl0Ty01LUZ3SnctSTBvSSIsInN1YiI6Inl1eGlhbmcuY2h1IiwiaWF0IjoxNjUzNTMwMDA0LCJleHAiOjE2NTM3ODkyMDQsImxhc3RSZWZyZXNoVGltZSI6MTY1MzUzMDAwNDMwMiwieHNyZlRva2VuIjoiajZkOG9lOGY3dGJydjU4OWRtZzJmZ24yZ2oifQ.pWvcY3V2QeZDoEHe8FKJRSAFT_AJUlP7e4a57kRJL1s");
            conn.setRequestMethod("GET");
            // 建立实际的连接
            conn.connect();
            // 定义输入流来读取URL的响应
            in = conn.getInputStream();
            result = StreamUtils.copyToString(in, Charset.forName("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSONObject.parseObject(result);
    }
 
    /**
     * 发送 post 请求
     *
     * @throws Exception
     */
    public static String post(String url, String paramStr) {
        InputStream in = null;
        OutputStream os = null;
        String result = "";
        try {
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) new URL(url)
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            // 发送POST请求须设置
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            os = conn.getOutputStream();
            // 注意编码格式，防止中文乱码
            if (StringUtils.hasText(paramStr)) {
                os.write(paramStr.getBytes("utf-8"));
                os.close();
            }
            in = conn.getInputStream();
            result = StreamUtils.copyToString(in, Charset.forName("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
 
    public static String toParamString(Map<String, String> paramMap) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String,String>> entrySet= paramMap.entrySet();
        for(Map.Entry<String,String> entry : entrySet){
            String key = entry.getKey();
            String value = entry.getValue();
            if(StringUtils.hasLength(key)){
                sb.append(key).append("=")
                        .append(URLEncoder.encode(value, "UTF-8"))
                        .append("&");
            }
        }
        return (sb.deleteCharAt(sb.length() - 1)).toString();
    }
}
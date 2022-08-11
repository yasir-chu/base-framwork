package com.chuyx.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuyx.service.SonarRobotService;
import com.chuyx.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author yuxiang.chu
 * @date 2022/4/15 18:31
 **/
@Slf4j
@Component
public class SonarRobotServiceImpl implements SonarRobotService {

    /**
     * 提醒类型集合
     */
    public static List<String> TYPE_LIST = Arrays.asList("BUG", "VULNERABILITY", "CODE_SMELL");

    /**
     * 提醒类型集合
     */
    public static Map<String, String> TYPE_MAP = initTypeMap();

    @Scheduled(cron = "0 30 9,11,15,18 * * ?")
    public void sonarRobot() {
        findSonarQube();
    }

    /**
     * 初始化提醒类型map
     *
     * @return 结果
     */
    private static Map<String, String> initTypeMap() {
        HashMap<String, String> typeMap = new HashMap<>(2);
        typeMap.put("BUG", "BUG");
        typeMap.put("VULNERABILITY", "漏洞");
        typeMap.put("CODE_SMELL", "坏味道");
        return typeMap;
    }

    /**
     * 休假人员 map
     */
    public static final Map<String, String> HOLIDAY_WORK = initHolidayHashMap();

    /**
     * 初始化休假人员 map
     *
     * @return 结果
     */
    private static Map<String, String> initHolidayHashMap() {
        Map<String, String> stringStringHashMap = new HashMap<>(2);
        stringStringHashMap.put("leo.li", "1");
        // 休假的人不提醒
        // stringStringHashMap.put("marxx.ma", "1");
        return stringStringHashMap;
    }

    /**
     * 请求Sonar质量阈信息
     */
    @Override
    public void findSonarQube() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            System.out.println("今天是周末；");
            return;
        }

        List<String> sysList = Arrays.asList("t8t-mid-uc", "t8t-mid-uct");
        StringBuilder sendMessage = new StringBuilder();
        sendMessage.append("大家注意了，以下bugs和漏洞数请在一小时内修复，否则再次告警：\n");
        boolean flag = false;
        for (String sys : sysList) {
            sendMessage.append("系统：").append(sys).append("\n");
            List<String> itemList = new ArrayList<>(TYPE_LIST);
            for (String type : itemList) {

                String url = "http://10.50.1.121:9000/api/issues/search?componentKeys=t8t.mid%3A" + sys + "&s=FILE_LINE&resolved=false&sinceLeakPeriod=true&types=" + type + "&ps=100&facets=severities%2Ctypes&additionalFields=_all";
                JSONObject jsonObject = curl(url);
                Map<String, String> userMap = getUserMap(jsonObject.getJSONArray("users"));
                Map<String, Integer> issues = getBugsTongji(jsonObject.getJSONArray("issues"));
                log.info("当前系统：{}, 当前类型：{}，结果：{}", sys, type, issues);
                if (issues.size() > 0) {
                    sendNotice(issues, userMap, type, sendMessage, "http://10.50.1.121:9000/project/issues?id=t8t.mid%3A" + sys + "&resolved=false&sinceLeakPeriod=true&types=" + type);
                    flag = true;
                }
            }
        }

        if (flag) {
            sendRobotMessage(sendMessage.toString(), "3d96e327-fdb7-42a7-bc87-95f1eb38fbd2");
            // 调试注释
            // System.out.println(sendMessage.toString());
        }
    }

    /**
     * 统计通知
     */
    private static void sendNotice(Map<String, Integer> issues, Map<String, String> userMap, String type, StringBuilder sendMessage, String url) {
        sendMessage.append("类型：").append(type).append("(").append(TYPE_MAP.get(type)).append(")").append("\n");
        sendMessage.append("url：").append(url).append("\n");
        issues.forEach((k, v) -> {
            String name = userMap.get(k);
            sendMessage.append("\t").append(name).append("(").append(k).append(")").append("---").append(v).append("个").append("\n");
        });
    }

    private static Map<String, Integer> getBugsTongji(JSONArray issues) {
        if (issues == null || issues.size() == 0) {
            return Collections.emptyMap();
        }
        Map<String, Integer> mesMap = new HashMap<>(16);
        for (Object bug : issues) {
            JSONObject item = (JSONObject) bug;
            String name = item.getString("assignee");
            if (HOLIDAY_WORK.containsKey(name)) {
                continue;
            }
            if (mesMap.containsKey(name)) {
                mesMap.put(name, mesMap.get(name) + 1);
            } else {
                mesMap.put(name, 1);
            }
        }
        return mesMap;
    }

    /**
     * 获取用户
     *
     * @param users 集合
     * @return 结果
     */
    private static Map<String, String> getUserMap(JSONArray users) {
        if (users == null || users.size() == 0) {
            return Collections.emptyMap();
        }
        Map<String, String> res = new HashMap<>(16);
        for (Object user : users) {
            JSONObject item = (JSONObject) user;
            res.put(item.getString("login"), item.getString("name"));
        }
        return res;
    }


    /**
     * 发送机器信息
     */
    public static void sendRobotMessage(String content, String robotKey) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=" + robotKey;
        JSONObject postData = new JSONObject();
        postData.put("msgtype", "text");
        JSONObject contentJson = new JSONObject();
        contentJson.put("content", content);
        postData.put("text", contentJson);
        HttpUtil.post(url, postData.toString());
    }

    /**
     * curl请求
     *
     * @param url 请求链接
     * @return 解雇
     */
    public static JSONObject curl(String url) {
        url = "\"" + url + "\"";
        String[] cmds = {"curl", "-u", "\"yuxiang.chu:yuxiang.chu@corp.to8to.com\"", "-X", "GET", "" + url + "", "-H", "\"Accept:application/json\""};
        StringBuilder sb = new StringBuilder();
        for (String cmd : cmds) {
            sb.append(cmd).append(" ");
        }
        String responseMsg = execCurl(cmds);
        return JSONObject.parseObject(responseMsg);
    }

    public static String execCurl(String[] cmds) {
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();

        } catch (IOException e) {
            System.out.print("获取线程异常" + e);
        }
        return null;
    }

}

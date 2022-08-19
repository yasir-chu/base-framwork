package com.chuyx.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chuyx
 * @date 2021/5/4 16:34
 */
@Api(tags = "测试模块")
@ResponseBody
public interface TestApi {

    /**
     * 查询所有学生信息
     *
     * @return 所有学生信息
     */
    @PostMapping(value = "/views/test/test")
    String test();

}

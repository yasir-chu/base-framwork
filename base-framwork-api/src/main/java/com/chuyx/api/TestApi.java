package com.chuyx.api;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yuxiang.chu
 * @date 2022/8/17 14:17
 **/
@Api(tags = "测试")
@ResponseBody
public interface TestApi {

    @PostMapping(value = "views/test/apollo/value")
    public void testApollo();
}

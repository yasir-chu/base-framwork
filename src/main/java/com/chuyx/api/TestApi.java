package com.chuyx.api;

import com.chuyx.vo.ResultPage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chuyx
 * @date 2021/5/4 16:34
 */
@Api(tags = "常用api")
@ResponseBody
public interface TestApi {

    /**
     * 测试-抛出异常
     * @return 结果
     */
    @PostMapping(value = "/views/test/exceptionTest")
    ResultPage<String> exceptionTest();

    /**
     * 测试-异常成功
     * @return 结果
     */
    @PostMapping(value = "/views/test/success")
    ResultPage<String> success();

    /**
     * 测试-post请求
     * @return
     */
    @PostMapping(value = "/views/test/post")
    ResultPage<String> post();

    @PostMapping(value = "/views/test/mqSendSimpleMessage")
    ResultPage<String> mqSendSimpleMessage(String message);
}

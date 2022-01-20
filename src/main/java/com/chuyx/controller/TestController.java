package com.chuyx.controller;

import com.chuyx.api.TestApi;
import com.chuyx.contant.CodeMsg;
import com.chuyx.exception.CommonException;
import com.chuyx.vo.ResultPage;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.core.codec.CodecException;
import org.springframework.stereotype.Controller;

/**
 * @author yuxiang.chu
 * @date 2022/1/20 14:48
 **/
@Controller
public class TestController implements TestApi {

    @Override
    public ResultPage<String> exceptionTest() {
        throw new CommonException(new CodeMsg(10001, "参数错误"));
    }

    @Override
    public ResultPage<String> success() {
        return ResultPage.data("123456");
    }
}

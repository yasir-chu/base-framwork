package com.chuyx.handler;

import com.chuyx.contant.CodeMsg;
import com.chuyx.exception.CommonException;
import com.chuyx.vo.ResultPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yuxiang.chu
 * @date 2022/1/20 14:39
 **/
@ControllerAdvice
@Configuration
public class CommonExceptionHandle {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ResultPage> CommonExceptionHandle(CommonException e){
        // 获得异常消息
        CodeMsg codeMsg = e.getCodeMsg();
        // 设置错误消息页面返回
        return ResponseEntity.status(HttpStatus.OK).body(ResultPage.fail(codeMsg));
    }
}

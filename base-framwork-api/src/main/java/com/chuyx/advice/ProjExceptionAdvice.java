package com.chuyx.advice;

import com.chuyx.res.CommonException;
import com.chuyx.res.ResCodeEnum;
import com.chuyx.res.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yuxiang.chu
 * @date 2022/8/25 16:24
 **/
@RestControllerAdvice
@Slf4j
public class ProjExceptionAdvice {

    @ExceptionHandler
    public ResResult<String> doException(Exception e) {
        log.error("[异常捕获]");
        if (e instanceof CommonException) {
            return new ResResult<>(ResCodeEnum.FAIL.getCode(), ((CommonException)e).getNoticeMessage(), null);
        }
        return new ResResult<>(ResCodeEnum.FAIL.getCode(), e.getMessage(), null);
    }
}

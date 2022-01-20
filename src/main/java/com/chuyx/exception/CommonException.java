package com.chuyx.exception;

import com.chuyx.contant.CodeMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 * @author yuxiang.chu
 * @date 2022/1/20 14:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonException extends RuntimeException{

    /**
     *  引入自定义异常消息
     */
    private CodeMsg codeMsg;


}

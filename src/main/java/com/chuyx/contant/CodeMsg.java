package com.chuyx.contant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author yuxiang.chu
 * @date 2022/1/20 14:15
 **/
@NoArgsConstructor
@AllArgsConstructor
public class CodeMsg {


    public static CodeMsg success(){
        return new CodeMsg(200, "success");
    }

    public static CodeMsg serviceError(){
        return new CodeMsg(500, "服务器异常");
    }


    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示消息
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

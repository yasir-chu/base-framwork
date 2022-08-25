package com.chuyx.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuxiang.chu
 * @date 2022/8/25 15:00
 **/
@Getter
@AllArgsConstructor
public enum ResCodeEnum {

    /**
     * 自定义异常code枚举
     */
    SUC(0, "成功"),
    FAIL(500, "服务端错误"),
    PARAM_ERROR(10010, "参数错误");


    /**
     * 错误码
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;
}

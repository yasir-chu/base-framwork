package com.chuyx.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuxiang.chu
 * @date 2022/8/25 14:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonException extends RuntimeException {

    private Integer errcode;


    public CommonException(String noticeMessage) {
        super(noticeMessage);
        this.errcode = ResCodeEnum.FAIL.getCode();
    }
}

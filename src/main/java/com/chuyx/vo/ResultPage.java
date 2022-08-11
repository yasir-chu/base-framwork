package com.chuyx.vo;

import com.alibaba.fastjson.JSON;
import com.chuyx.contant.CodeMsg;
import com.chuyx.exception.CommonException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuxiang.chu
 * @date 2022/1/20 14:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPage<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T date;

    ResultPage(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultPage<String> data(T data){
        try{
            return ResultPage.success(CodeMsg.success(), JSON.toJSONString(data));
        }catch (CommonException e){
            return ResultPage.fail(new CodeMsg(e.getCodeMsg().getCode(), e.getCodeMsg().getMsg()));
        }catch (Exception e){
            return ResultPage.fail(new CodeMsg(500, e.getMessage()));
        }
    }

    public static <T> ResultPage<T> success(CodeMsg codeMsg, T data) {
        return new ResultPage<>(codeMsg.getCode(), codeMsg.getMsg(), data);
    }

    public static <T> ResultPage<T> fail(CodeMsg codeMsg) {
        return new ResultPage<>(codeMsg.getCode(), codeMsg.getMsg());
    }
}

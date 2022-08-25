package com.chuyx.res;

/**
 * @author yuxiang.chu
 * @date 2022/8/25 14:42
 **/
public class ResUtils {

    private static final String SUC_DESC;
    private static final int SUC = 0;
    private static final int FAIL = 10000;
    private static final String FAIL_DESC;

    public ResUtils() {
    }

    public static ResResult suc(String data) {
        return result(SUC, SUC_DESC, data);
    }

    public static <T> ResResult<T> data(T data) {
        return result(SUC, SUC_DESC, data);
    }

    public static ResResult suc() {
        return suc((String) null);
    }

    public static ResResult fail(String desc) {
        return fail(FAIL, desc);
    }

    public static ResResult fail(int status, String desc) {
        return result(status, desc, (Object) null);
    }

    private static <T> ResResult<T> result(int code, String desc, T data) {
        return new ResResult(code, desc, data);
    }

    static {
        SUC_DESC = Boolean.TRUE.toString();
        FAIL_DESC = Boolean.FALSE.toString();
    }
}

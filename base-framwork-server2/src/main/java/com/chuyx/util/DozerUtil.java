package com.chuyx.util;

import org.dozer.DozerBeanMapper;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据对象转化工具类
 * @author yasir.chu
 * @date 2020/12/19
 **/

public class DozerUtil {

    public static DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * 单个数据对象转化
     *
     * @param source      数据源对象
     * @param targetClass 数据目标对象字节码
     * @param <T>         数据传输对下个的类的字节码
     * @return 转化好了之后的对象
     */
    public static <T> T map(java.lang.Object source, java.lang.Class<T> targetClass) {
        if (source != null) {
            return mapper.map(source, targetClass);
        }
        return null;
    }

    /**
     * 集合数据对象转化
     *
     * @param sources     数据源对象列表
     * @param targetClass 数据目标对象字节码
     * @param <T>         数据传输对下个的类的字节码
     * @return 转化好了之后的对象列表
     */
    public static <T> List<T> mapList(List<?> sources, Class<T> targetClass) {
        if (!CollectionUtils.isEmpty(sources)) {
            return sources.stream().map(source -> mapper.map(source, targetClass)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}

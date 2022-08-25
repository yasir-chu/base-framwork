package com.chuyx.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 * @author yuxiang.chu
 * @date 2022/8/25 16:44
 **/
@Data
@AllArgsConstructor
public class PageResult<T> {

    private Long total;

    private List<T> data;


}

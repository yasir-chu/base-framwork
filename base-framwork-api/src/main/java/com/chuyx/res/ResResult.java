package com.chuyx.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuxiang.chu
 * @date 2022/8/25 14:42
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("返回结果")
public class ResResult<T> {
    @ApiModelProperty(
            value = "返回状态(0成功，否则代表失败)",
            required = true
    )
    private Integer status;
    @ApiModelProperty(
            value = "返回说明，如果失败，则会有说明信息",
            required = true
    )
    private String desc;
    @ApiModelProperty(
            value = "返回数据",
            required = true
    )
    private T data;
}

package com.chuyx.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuxiang.chu
 * @date 2022/8/25 14:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVO {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("名字")
    private String name;
}

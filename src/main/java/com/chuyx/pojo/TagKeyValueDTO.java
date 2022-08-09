package com.chuyx.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagKeyValueDTO {

    @ApiModelProperty("标签key")
    private String key;

    @ApiModelProperty("标签值")
    private String value;
}
package com.chuyx.wrapper;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chuyx
 */
public class UserWrapper {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel("UserWrapper.StudentWrapper")
    public static class StudentWrapper {

        @ApiModelProperty("主键id")
        @NotNull
        private Integer id;

    }
}

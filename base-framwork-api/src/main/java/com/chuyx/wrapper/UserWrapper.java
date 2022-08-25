package com.chuyx.wrapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel("UserWrapper.SavaDTO")
    public static class SavaDTO {

        private Integer id;

        private String name;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel("UserWrapper.QueryPageDTO")
    public static class QueryPageDTO {

        @NotNull
        private Integer page;

        @NotNull
        private Integer size;

        private String name;

    }
}

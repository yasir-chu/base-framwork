package com.chuyx.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author cyx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student")
public class Student {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;
}

package com.chuyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyx.pojo.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chuyx
 * @date 2021/5/4 16:31
 */
@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {
}

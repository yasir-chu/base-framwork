package com.chuyx.api;

import com.chuyx.pojo.po.Student;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chuyx
 * @date 2021/5/4 16:34
 */
@Api(tags = "测试模块")
@ResponseBody
public interface StudentApi {

    /**
     * 查询所有学生信息
     * @return 所有学生信息
     */
    @PostMapping(value = "/views/student/queryAllStudent")
    String queryAllStudent();

    /**
     * 根据学生id查找学生信息
     * @param student 学生id
     * @return 学生信息
     */
    @PostMapping(value = "/views/student/queryStudentById")
    String queryStudentById(@RequestBody Student student);
}

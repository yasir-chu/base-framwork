package com.chuyx.controller;


import com.alibaba.fastjson.JSON;
import com.chuyx.api.StudentApi;
import com.chuyx.contant.NormalConstant;
import com.chuyx.pojo.po.Student;
import com.chuyx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

/**
 * @author chuyx
 * @date 2021/5/4 16:44
 */
@Controller
public class StudentController implements StudentApi {

    @Autowired
    private StudentService studentService;

    @Override
    public String queryAllStudent() {
        return JSON.toJSONString(studentService.queryAllStudent());
    }

    @Override
    public String queryStudentById(Student student) {
        if (student.getId() == null){
            return "";
        }
        return JSON.toJSONString(studentService.queryStudentById(student.getId()));
    }
}

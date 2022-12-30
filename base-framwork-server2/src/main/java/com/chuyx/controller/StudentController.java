package com.chuyx.controller;


import com.alibaba.fastjson.JSON;
import com.chuyx.api.StudentApi;
import com.chuyx.service.StudentService;
import com.chuyx.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
        return JSON.toJSONString("Service2222");
    }

    @Override
    public String queryStudentById(UserWrapper.StudentWrapper student) {
        if (student.getId() == null){
            return "";
        }
        return JSON.toJSONString("Service2222");
    }

}

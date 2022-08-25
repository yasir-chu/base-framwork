package com.chuyx.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.api.StudentApi;
import com.chuyx.res.PageResult;
import com.chuyx.res.ResResult;
import com.chuyx.res.ResUtils;
import com.chuyx.service.StudentService;
import com.chuyx.vo.StudentVO;
import com.chuyx.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author chuyx
 * @date 2021/5/4 16:44
 */
@Controller
public class StudentController implements StudentApi {

    @Autowired
    private StudentService studentService;

    @Override
    public ResResult<List<StudentVO>> queryAllStudent() {
        return ResUtils.data(studentService.queryAllStudent());
    }

    @Override
    public ResResult<StudentVO> queryStudentById(UserWrapper.StudentWrapper student) {
        return ResUtils.data(studentService.queryStudentById(student.getId()));
    }

    @Override
    public ResResult<Integer> save(UserWrapper.SavaDTO savaDTO) {
        return ResUtils.data(studentService.save(savaDTO));
    }

    @Override
    public ResResult<PageResult<StudentVO>> queryPage(UserWrapper.QueryPageDTO queryPageDTO) {
        return ResUtils.data(studentService.queryPage(queryPageDTO));
    }

    @Override
    public ResResult<Integer> testException() {
        return ResUtils.data(studentService.testException());
    }

}

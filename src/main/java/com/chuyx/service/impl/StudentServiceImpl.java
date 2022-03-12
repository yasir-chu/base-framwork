package com.chuyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chuyx.mapper.StudentMapper;
import com.chuyx.pojo.po.Student;
import com.chuyx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chuyx
 * @date 2021/5/4 16:46
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> queryAllStudent() {
        return studentMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Student queryStudentById(Integer id) {
        return studentMapper.selectById(id);
    }
}

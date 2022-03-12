package com.chuyx.service;

import com.chuyx.pojo.po.Student;

import java.util.List;

/**
 * @author chuyx
 * @date 2021/5/4 16:46
 */
public interface StudentService {

    /**
     * 查询所有学生信息
     * @return 所有学生信息
     */
    List<Student> queryAllStudent();

    /**
     * 根据学生id查询学生信息
     * @param id 学生id
     * @return 学生信息
     */
    Student queryStudentById(Integer id);
}

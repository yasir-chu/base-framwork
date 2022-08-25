package com.chuyx.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.res.PageResult;
import com.chuyx.vo.StudentVO;
import com.chuyx.wrapper.UserWrapper;

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
    List<StudentVO> queryAllStudent();

    /**
     * 根据学生id查询学生信息
     * @param id 学生id
     * @return 学生信息
     */
    StudentVO queryStudentById(Integer id);

    /**
     * 保存学生信息
     * @param savaDTO 待保存信息
     * @return 结果
     */
    Integer save(UserWrapper.SavaDTO savaDTO);

    Integer testException();

    PageResult<StudentVO> queryPage(UserWrapper.QueryPageDTO queryPageDTO);
}

package com.chuyx.api;

import com.chuyx.res.PageResult;
import com.chuyx.res.ResResult;
import com.chuyx.vo.StudentVO;
import com.chuyx.wrapper.UserWrapper;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chuyx
 * @date 2021/5/4 16:34
 */
@Api(tags = "测试模块")
@ResponseBody
public interface StudentApi {

    /**
     * 查询所有学生信息
     *
     * @return 所有学生信息
     */
    @PostMapping(value = "/views/student/queryAllStudent")
    ResResult<List<StudentVO>> queryAllStudent();

    /**
     * 根据学生id查找学生信息
     *
     * @param student 学生id
     * @return 学生信息
     */
    @PostMapping(value = "/views/student/queryStudentById")
    ResResult<StudentVO> queryStudentById(@RequestBody @Validated UserWrapper.StudentWrapper student);

    @PostMapping(value = "/views/student/save")
    ResResult<Integer> save(@RequestBody @Validated UserWrapper.SavaDTO savaDTO);

    @PostMapping(value = "/views/student/queryPage")
    ResResult<PageResult<StudentVO>> queryPage(@RequestBody @Validated UserWrapper.QueryPageDTO queryPageDTO);

    @PostMapping(value = "/views/student/testException")
    ResResult<Integer> testException();
}

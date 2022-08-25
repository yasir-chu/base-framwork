package com.chuyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.mapper.StudentMapper;
import com.chuyx.pojo.po.Student;
import com.chuyx.res.CommonException;
import com.chuyx.res.PageResult;
import com.chuyx.service.StudentService;
import com.chuyx.util.DozerUtil;
import com.chuyx.vo.StudentVO;
import com.chuyx.wrapper.UserWrapper;
import org.apache.commons.lang3.StringUtils;
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
    public List<StudentVO> queryAllStudent() {
        return DozerUtil.mapList(studentMapper.selectList(new QueryWrapper<>()), StudentVO.class);
    }

    @Override
    public StudentVO queryStudentById(Integer id) {
        return DozerUtil.map(studentMapper.selectById(id), StudentVO.class);
    }

    @Override
    public Integer save(UserWrapper.SavaDTO savaDTO) {
        if (savaDTO.getId() == null) {
            // 新增
            if (StringUtils.isEmpty(savaDTO.getName())) {
                throw new CommonException("学生不可以没有名字！");
            }
            return studentMapper.insert(DozerUtil.map(savaDTO, Student.class));
        }
        // 更新
        Student student = studentMapper.selectById(savaDTO.getId());
        if (student==null) {
            throw new CommonException("没有原本学生信息");
        }
        if (student.getName().equals(savaDTO.getName())) {
            throw new CommonException("没有进行更改");
        }
        return studentMapper.updateById(DozerUtil.map(savaDTO, Student.class));
    }

    @Override
    public Integer testException() {
        return 1/0;
    }

    @Override
    public PageResult<StudentVO> queryPage(UserWrapper.QueryPageDTO queryPageDTO) {
        IPage<Student> page = new Page<>(queryPageDTO.getPage(), queryPageDTO.getSize());
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(queryPageDTO.getName())) {
            studentQueryWrapper.like("name", queryPageDTO.getName());
        }
        IPage<Student> result = studentMapper.selectPage(page, studentQueryWrapper);
        List<Student> records = result.getRecords();
        List<StudentVO> rows = DozerUtil.mapList(records, StudentVO.class);
        return new PageResult<>(result.getTotal(), rows);
    }
}

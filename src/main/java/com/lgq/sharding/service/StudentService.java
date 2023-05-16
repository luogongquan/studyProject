package com.lgq.sharding.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgq.sharding.entity.Student;
import com.lgq.sharding.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 17:14
 * @Description:
 **/
@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {
    @Autowired
    private StudentMapper mapper;
    public List<Student> getList(String time){
        return mapper.getList(time);
    }
}

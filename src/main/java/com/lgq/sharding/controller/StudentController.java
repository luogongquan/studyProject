package com.lgq.sharding.controller;

import com.lgq.sharding.entity.Student;
import com.lgq.sharding.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 18:13
 * @Description:
 **/
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService service;
    @RequestMapping("getList")
    public List<Student> getList(String time){
        return service.getList(time);
    }
}

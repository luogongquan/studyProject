package com.lgq.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgq.sharding.entity.Sharding;
import com.lgq.sharding.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 17:09
 * @Description:
 **/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select * from student where time=${time}")
    List<Student> getList(@Param("time") String time);

    @Select("select id,count(1)count from student group by id having count(1)>0")
    List<Map> getCount();
}

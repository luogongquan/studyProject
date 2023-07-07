package com.lgq.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgq.sharding.entity.Sharding;
import com.lgq.sharding.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 17:09
 * @Description:
 **/
public interface ShardingMapper extends BaseMapper<Sharding> {
    @Select("select * from sharding where date_time=#{time}")
    List<Sharding> getList(@Param("time") String time);


    @Select("select * from sharding where code =  '202301' ")
    List<Sharding> get4String();

    @Select("select * from sharding where date_time between  '2023-01-02' and '2023-02-05'")
    List<Sharding> get4Date();
}

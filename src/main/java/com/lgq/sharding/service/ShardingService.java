package com.lgq.sharding.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgq.sharding.entity.Sharding;
import com.lgq.sharding.mapper.ShardingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 17:14
 * @Description:
 **/
@Service
public class ShardingService extends ServiceImpl<ShardingMapper, Sharding> {
    @Autowired
    private ShardingMapper mapper;


    public List<Sharding> getList(String time){
        return mapper.getList(time);
    }

    public List<Sharding> get4String(){
        return mapper.get4String();
    }
    public List<Sharding> get4Date(){
        return mapper.get4Date();
    }
}

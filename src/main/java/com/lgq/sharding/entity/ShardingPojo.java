package com.lgq.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 17:13
 * @Description:
 **/

@Data
public class ShardingPojo implements Comparable<String>{
    private String startTime;
    private String endTime;
    private String time;

    @Override
    public int compareTo(String o) {
        return 0;
    }
}

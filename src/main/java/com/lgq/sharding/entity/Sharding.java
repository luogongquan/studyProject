package com.lgq.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 17:13
 * @Description:
 **/
@Data
public class Sharding {
    private int id;
    private Date time;
    private Date createdTime;
    @TableField(exist = false)
    private Date startTime;
    @TableField(exist = false)
    private Date endTIme;
}

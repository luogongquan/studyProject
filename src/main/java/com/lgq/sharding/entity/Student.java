package com.lgq.sharding.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 18:11
 * @Description:
 **/
@Data
public class Student implements Serializable{
    private long id;
    private Date time;
}

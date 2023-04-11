package com.lgq.redislistener.jwt.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: luogongquan
 * @date: 2023/4/11 9:35
 * @Description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private Integer id;

    private String account;
    private String userName;
    private String password;
}

package com.lgq.redislistener.jwt.controller;

import com.lgq.redislistener.jwt.pojo.User;
import com.lgq.redislistener.jwt.service.UserService;
import com.lgq.redislistener.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: luogongquan
 * @date: 2023/4/11 9:37
 * @Description:
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user){
        Map<String, Object> result = new HashMap<>();
        try {
            User login = service.login(user);
            //存储载荷声明参数map
            HashMap<String, String> map = new HashMap<>();
            map.put("username", login.getUserName());
            //生成JWT令牌
            String token = JwtUtil.getToken(map);
            //通过验证，将相关用户信息及token等存入map，用于返回
            result.put("state", true);
            result.put("msg", "认证成功");
            result.put("token", token);
        } catch (Exception e) {
            result.put("state", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/test")
    public String test(){
        return "成功";
    }
}

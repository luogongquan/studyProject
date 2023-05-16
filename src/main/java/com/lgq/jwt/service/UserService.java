package com.lgq.jwt.service;

import com.lgq.jwt.pojo.User;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Author: luogongquan
 * @date: 2023/4/11 9:35
 * @Description:
 **/
@Service
public class UserService {
    public static void main(String[] args) {
        StringBuilder append = new StringBuilder().append("20230601", 0, 6);
        System.out.println(append.toString());
    }
    public User login(User user) throws Exception {
        if (!UserInfo.getEnum(user.getAccount()).getPassword().equals(user.getPassword())){
            throw new Exception("密码错误");
        }
        return new User(1,"9527","张三","123456");
    }
}

@Getter
enum UserInfo{
    INFO1("9527","123456"),
    INFO2("9528","123457");
    private String account;
    private String password;
    UserInfo(String account,String password){
        this.account=account;
        this.password=password;
    }
    public static UserInfo getEnum(String account){
        UserInfo userInfo = Arrays.stream(UserInfo.values()).filter(info -> info.getAccount().equals(account)).findAny().orElse(null);
        return userInfo;
    }
}
package com.lgq.enumFunction;

import com.lgq.jwt.pojo.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @Author: luogongquan
 * @date: 2023/4/11 18:17
 * @Description:
 **/
@Getter
public enum EnumStrage {
    TYPEONE("100",item->{
        return null;
    });
    private String code;
    private Function<String, User> func;
    EnumStrage(String code,Function<String, User> func){
        this.code=code;
        this.func=func;
    }
}

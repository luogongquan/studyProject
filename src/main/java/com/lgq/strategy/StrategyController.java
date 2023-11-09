package com.lgq.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: StrategyController
 * @author: luogongquan
 * @since: 2023/7/31 18:41
 */
@RestController
@RequestMapping("/strategy")
public class StrategyController {
    @Autowired
    private StrategyFactory factory;
    @RequestMapping("/test")
    public void test(String code){
        factory.handle(code);
    }
}

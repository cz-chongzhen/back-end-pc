package cn.cz.czbase.controller;

import cn.cz.czbase.config.NoTokenVerify;
import cn.cz.czbase.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base-service/common")
public class ReidsController {
    @Autowired
    private JedisUtil jedisUtil;
    @RequestMapping("/generateId")
    @NoTokenVerify
    public long generateId(){
        long id = jedisUtil.generateId();
        return id;
    }
}

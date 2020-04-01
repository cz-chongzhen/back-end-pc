package cn.cz.czbase.controller;

import cn.cz.czbase.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class ReidsController {
    @Autowired
    private JedisUtil jedisUtil;
    @RequestMapping("/generateId")
    public long generateId(){
        long id = jedisUtil.generateId();
        return id;
    }
}

package cn.cz.czauth.service.impl;

import cn.cz.czauth.entity.AppResponse;
import cn.cz.czauth.entity.User;
import cn.cz.czauth.service.RedisUtilService;
import cn.cz.czauth.service.UserLoginService;
import cn.cz.czauth.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private RedisUtilService redisUtilService;

    @Override
    public AppResponse registerUser(User user) {
        AppResponse appResponse = new AppResponse();
        long userId = redisUtilService.generateId();
        user.setId(userId);
        DateTime dateTime = new DateTime();
        user.setCreateDateTime(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
        //管理员注册
        user.setCreator(10001);
        userLoginService.regist(user);
        appResponse.setMessage("注册成功");
        appResponse.setStatusCode(200);
        appResponse.setAppData(user);
        return appResponse;
    }
}

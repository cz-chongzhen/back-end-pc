package cn.cz.czbase.controller;

import cn.cz.czbase.config.NoTokenVerify;
import cn.cz.czbase.entity.User;
import cn.cz.czbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/base-service/user")
public class SysUserController {
    @Autowired
    private UserService userService;

    @NoTokenVerify
    @RequestMapping("/findUser")
    public User findUser(@RequestBody User user){
        User user1 = userService.findUser(user);
        return user1;
    }

    @NoTokenVerify
    @RequestMapping("/registerUser")
    public User register(@RequestBody User user){
        userService.registerUser(user);
        return user;
    }

}

package cn.cz.czbase.controller;

import cn.cz.czbase.entity.User;
import cn.cz.czbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class SysUserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findUser")
    public User findUser(@RequestBody User user){
        User user1 = new User();
        user1 = userService.findUser(user);
        return user1;
    }
}

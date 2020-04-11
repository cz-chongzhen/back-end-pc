package cn.cz.czauth.controller;

import cn.cz.czauth.config.NoTokenVerify;
import cn.cz.czauth.entity.AppResponse;
import cn.cz.czauth.entity.User;
import cn.cz.czauth.client.CzBaseService;
import cn.cz.czauth.service.UserService;
import cn.cz.czauth.util.JwtUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth-service/account")
public class UserLogin {

    @Autowired
    private CzBaseService czBaseService;

    @Autowired
    private UserService userService;
    /**
     * 用户登陆方法
     * @param loginUser  传入用户名和密码
     * @return
     */
    @RequestMapping(value = "/login")
    @NoTokenVerify
    public AppResponse userLogin(@RequestBody User loginUser){
        AppResponse appResponse = new AppResponse();
        User checkUser = new User();
        Map map = new HashMap();
        //通过用户名查找用户信息
        checkUser = czBaseService.findUser(loginUser);
        if(checkUser!=null){
            if(checkUser.getPassWord().equals(loginUser.getPassWord())){
                appResponse.setAppData(checkUser);
                DateTime dateTime = new DateTime();
                //设置token有效时间为60分钟
                dateTime = dateTime.plusMinutes(60);
                long expirems = dateTime.plusMinutes(60).getMillis();
                String access_token  = JwtUtil.createJWT(expirems,checkUser);
                map.put("user",checkUser);
                map.put("access_token",access_token);
                appResponse.setAppData(map);
                appResponse.setStatusCode(200);
                appResponse.setMessage("登陆成功");
            }else{
                appResponse.setMessage("登陆失败，密码错误");
                appResponse.setStatusCode(101);
            }

        }else{
            appResponse.setMessage("系统无此用户，登陆失败");
        }
        return appResponse;
    }

    @RequestMapping(value = "/register")
    @NoTokenVerify
    public AppResponse registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

}

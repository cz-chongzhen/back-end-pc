package cn.cz.czauth.controller;

import cn.cz.czauth.config.CheckToken;
import cn.cz.czauth.config.LoginToken;
import cn.cz.czauth.dto.UserSession;
import cn.cz.czauth.entity.AppResponse;
import cn.cz.czauth.entity.User;
import cn.cz.czauth.service.JWTVertiftService;
import cn.cz.czauth.service.UserLoginService;
import cn.cz.czauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/account")
public class UserLogin {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private JWTVertiftService jwtVertiftService;

    /**
     * 用户登陆方法
     * @param loginUser  传入用户名和密码
     * @return
     */
    @RequestMapping(value = "/login/{userName}")
    @LoginToken
    public AppResponse userLogin(@RequestBody User loginUser){
        AppResponse appResponse = new AppResponse();
        User checkUser = new User();
        Map map = new HashMap();
        //通过用户名查找用户信息
        checkUser = userLoginService.findUser(loginUser);
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




    @RequestMapping(value = "/vertifyAccess_Token/{access_token}")
    public boolean vertifyAccessToken(@PathVariable(value = "access_token") String access_token){
        return jwtVertiftService.vertifyAccessToken(access_token);
    }


    @CheckToken
    @RequestMapping(value = "/testJWT/{access_token}")
    public AppResponse testJWT(@PathVariable("access_token") String access_token){
        User user = new User();
        Claims claims = JwtUtil.parseJWT(access_token);
        //获取登陆用户的id
        long userId = (Long)claims.get("id");
        AppResponse appResponse = new AppResponse();
        appResponse.setAppData(claims);
        return appResponse;
    }
}

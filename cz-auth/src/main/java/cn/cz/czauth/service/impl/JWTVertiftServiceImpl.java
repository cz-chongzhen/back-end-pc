package cn.cz.czauth.service.impl;

import cn.cz.czauth.entity.User;
import cn.cz.czauth.service.JWTVertiftService;
import cn.cz.czauth.service.UserLoginService;
import cn.cz.czauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JWTVertiftServiceImpl implements JWTVertiftService {

    @Autowired
    private UserLoginService userLoginService;

    @Override
    public boolean vertifyAccessToken(String access_token) {
        Claims claims = JwtUtil.parseJWT(access_token);
        long userId = Long.parseLong(claims.get("id").toString());
        String pwdJwt = (String)claims.get("password");
        User user = new User();
        user.setId(userId);
        user = userLoginService.findUser(user);
        if(user.getPassWord().equals(pwdJwt))
            return true;
        return false;
    }
}

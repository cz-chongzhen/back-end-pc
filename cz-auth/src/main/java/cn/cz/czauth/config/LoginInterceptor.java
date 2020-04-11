package cn.cz.czauth.config;

import cn.cz.czauth.dto.UserSession;
import cn.cz.czauth.entity.User;
import cn.cz.czauth.client.CzBaseService;
import cn.cz.czauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LoginInterceptor implements HandlerInterceptor {
//    @Autowired
//    private CzBaseService czBaseService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader("access_token");
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        System.out.println("---"+method.getExceptionTypes()+"*******");
        //检查是否有LoginToken注释，有则跳过认证
        if(method.isAnnotationPresent(NoTokenVerify.class)){
            NoTokenVerify loginToken = method.getAnnotation(NoTokenVerify.class);
            if (loginToken.required()) {
                return true;
            }
        }
        //有token的话往UserSession中写入userId  和userName
        if(token!=null && token.trim().length()>0){
            try{
                Claims claims = JwtUtil.parseJWT(token);
                UserSession.setProperty("userId",claims.get("id"));
                UserSession.setProperty("userName",claims.get("userName"));
            }catch (Exception e){
                throw new RuntimeException("解析JWT异常！");
            }
        }
//        if(token==null){
//            throw new RuntimeException("无token");
//        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if (checkToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                long userId;
                try {
                    Claims claims = JwtUtil.parseJWT(token);
                    userId = (Long)claims.get("id");
                } catch (Exception j) {
                    throw new RuntimeException("解析JWT异常！");
                }
                User paramUser = new User();
                paramUser.setId(userId);
//                User user = czBaseService.findUser(paramUser);
//                if (user == null) {
//                    throw new RuntimeException("用户不存在，请重新登录");
//                }
//                Boolean verify = JwtUtil.isVerify(token, user);
//                if (!verify) {
//                    throw new RuntimeException("非法访问！");
//                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}

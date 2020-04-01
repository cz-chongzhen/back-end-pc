package cn.cz.czauth.config;

import cn.cz.czauth.entity.User;
import cn.cz.czauth.service.UserLoginService;
import cn.cz.czauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserLoginService userLoginService;
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
        //检查是否有LoginToken注释，有则跳过认证
        if(method.isAnnotationPresent(LoginToken.class)){
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {
                return true;
            }
        }
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
                    throw new RuntimeException("访问异常！");
                }
                User paramUser = new User();
                paramUser.setId(userId);
                User user = userLoginService.findUser(paramUser);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                Boolean verify = JwtUtil.isVerify(token, user);
                if (!verify) {
                    throw new RuntimeException("非法访问！");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}

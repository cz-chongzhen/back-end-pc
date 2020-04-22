package cn.cz.czauth.config;

import cn.cz.czauth.dto.UserSession;
import cn.cz.czauth.entity.AppResponse;
import cn.cz.czauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class LoginInterceptor implements HandlerInterceptor {
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
                response.setStatus(401);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                AppResponse res = new AppResponse();
                res.setMessage("token验证失败");
                res.setStatusCode(401);
                PrintWriter out = null ;
                out = response.getWriter();
                out.write(res.toString());
                out.flush();
                out.close();
                return false;
            }
        }
        if(token==null){
            throw new RuntimeException("无token");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}

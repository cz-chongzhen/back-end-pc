package cn.cz.czauth.service;

import cn.cz.czauth.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "cz-service-base")
public interface UserLoginService {
    @RequestMapping(value = "/user/findUser",method = RequestMethod.POST)
    User findUser(@RequestBody User user);

    @RequestMapping(value = "/verifyAccessToken/{access_token}",method = RequestMethod.POST)
    boolean vertifyAccessToken(@PathVariable("access_token") String access_token,@RequestBody User user);

    @RequestMapping(value = "/user/registerUser")
    User regist(@RequestBody User user);


}

package cn.cz.czauth.client;

import cn.cz.czauth.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient("cz-service-base")
public interface CzBaseService {
    /**
     * 查找用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/base-service/user/findUser",method = RequestMethod.POST)
    User findUser(@RequestBody User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/base-service/user/registerUser")
    User regist(@RequestBody User user);

    /**
     * 去redis中获取id
     * @return
     */
    @RequestMapping(value = "/base-service/common/generateId")
    long generateId();

}

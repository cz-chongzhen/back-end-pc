package cn.cz.czauth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "cz-service-base")
public interface RedisUtilService {
    @RequestMapping(value = "/common/generateId")
    long generateId();
}

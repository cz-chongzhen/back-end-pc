package cn.cz.czauth.service;

import cn.cz.czauth.entity.AppResponse;
import cn.cz.czauth.entity.User;

public interface UserService {
    AppResponse registerUser(User user);
}

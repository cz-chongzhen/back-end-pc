package cn.cz.czbase.service;

import cn.cz.czbase.entity.User;

public interface UserService {
    User findUser(User user);
    User registerUser(User user);
}

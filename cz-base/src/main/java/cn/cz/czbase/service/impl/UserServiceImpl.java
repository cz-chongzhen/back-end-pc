package cn.cz.czbase.service.impl;

import cn.cz.czbase.dao.UserDAO;
import cn.cz.czbase.entity.User;
import cn.cz.czbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public User findUser(User user) {
        return userDAO.findUser(user);
    }

    @Override
    public User registerUser(User user) {
        userDAO.registerUser(user);
        return user;
    }





}

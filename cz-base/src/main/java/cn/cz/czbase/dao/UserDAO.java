package cn.cz.czbase.dao;

import cn.cz.czbase.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    User findUser(User user);

    int registerUser(User user);

    void update(User user);

    void update2(User user);
}


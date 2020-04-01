package cn.cz.czauth.service;

import cn.cz.czauth.entity.User;

public interface JWTVertiftService {

    boolean vertifyAccessToken(String access_token);
}

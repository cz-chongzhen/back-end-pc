package cn.cz.czauth.entity;

import lombok.Data;

@Data
public class User {
    private long id;
    private String userName;
    private String passWord;
    private String createDateTime;
    private int sex;
}

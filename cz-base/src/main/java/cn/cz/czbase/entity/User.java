package cn.cz.czbase.entity;

import lombok.Data;

@Data
public class User {
    private long id;
    private String userName;
    private String passWord;
    private String createDateTime;
    private String remark;
    private String mobile;
    private long creator;
}

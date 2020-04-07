package cn.cz.czbase.entity;

import lombok.Data;



@Data
public class SysTable {
    private long id;
    private String name;
    private String tableName;
    private String createDateTime;
    private String remark;
}

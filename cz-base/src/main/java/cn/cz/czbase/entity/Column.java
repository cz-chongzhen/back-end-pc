package cn.cz.czbase.entity;

import lombok.Data;

@Data
public class Column {
    //表字段 名称
    private String name;
    //字段类类型
    private String typeName;
    //是否为空
    private String isNull;
    //字段长度
    private int length;
    //备注
    private String comment;
}

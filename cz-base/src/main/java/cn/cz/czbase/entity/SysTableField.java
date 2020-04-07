package cn.cz.czbase.entity;

import lombok.Data;

@Data
public class SysTableField {
    private long id;
    private String name;
    private String fieldName;
    private String dataType;
    private int sort;
    private int length;
    private long tableId;
    private String remark;
    private String createDateTime;
    private String isNull;

}

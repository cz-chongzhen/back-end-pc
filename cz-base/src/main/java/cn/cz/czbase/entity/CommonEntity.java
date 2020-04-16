package cn.cz.czbase.entity;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class CommonEntity {
    //查询表的名字
    private String tableName;
    //需要查询的字段
    private List<String> columns;
    //查询记录的id
    private long id;
    //条件字段  并且条件
    private List<Map<String,Object>> conditionColumnsByAnd;

    private List<Map<String,Object>> conditionColumnsByOr;


    //需要更新的字段  暂时不用
    private List<Map<String,Object>> updateColumns;

    //更新条件   暂时不用
    private List<Map<String,Object>> updateCondition;

    //更新列
    private List<LinkedHashMap<String,Object>> updateList;


    //添加或者更细的 列的属性
    private LinkedHashMap<String,Object> fieldList;


    //删除列
    private List<Map<String,Object>> deleteList;

}

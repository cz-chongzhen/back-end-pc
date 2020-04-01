package cn.cz.czbase.entity;

import lombok.Data;

import java.util.List;

@Data
public class TableEntity {
    private String tableName;
    private List<Column> columns;
}

package cn.cz.czbase.service;

import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.Column;

import java.util.List;

public interface CreateTableService {
    public AppResponse createTable(String name, List<Column> columnList);

    AppResponse getFieldType();
}

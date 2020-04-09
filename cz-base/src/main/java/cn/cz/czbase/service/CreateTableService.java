package cn.cz.czbase.service;

import cn.cz.czbase.controller.CreateTableController;
import cn.cz.czbase.entity.*;

import java.util.List;

public interface CreateTableService {

    AppResponse createTable(CreateTableController.TableEntity tableEntity);

    AppResponse updateTable(CreateTableController.TableEntity tableEntity);

    AppResponse getTableField(Long tableId);

    AppResponse getTables();
}

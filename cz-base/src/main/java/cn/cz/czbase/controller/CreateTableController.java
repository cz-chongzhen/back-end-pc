package cn.cz.czbase.controller;

import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.SysTable;
import cn.cz.czbase.entity.SysTableField;
import cn.cz.czbase.service.CreateTableService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/base-service/table")
public class CreateTableController {

    @Autowired
    private CreateTableService createTableService;

    /**
     * 在数据库中创建表  以及 修改表
     * @param tableEntity
     * @return
     */
    @RequestMapping(value = "/createTable", method = RequestMethod.POST)
    public AppResponse createTable(@RequestBody TableEntity tableEntity){
        return createTableService.createTable(tableEntity);
    }

    @RequestMapping(value = "/queryTables",method = RequestMethod.GET)
    public AppResponse getTables(){
        return createTableService.getTables();
    }

    @RequestMapping(value = "/queryTableField/{tableId}",method = RequestMethod.GET)
    public AppResponse getTableField(@PathVariable("tableId") Long tableId){
        return createTableService.getTableField(tableId);
    }

    @Data
    public static class TableEntity {
        private SysTable sysTable;
        private List<SysTableField> sysTableFieldList;
    }
}

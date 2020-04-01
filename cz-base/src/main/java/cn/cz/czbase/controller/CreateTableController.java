package cn.cz.czbase.controller;

import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.Column;
import cn.cz.czbase.entity.TableEntity;
import cn.cz.czbase.service.CreateTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/base-service")
public class CreateTableController {
    @Autowired
    private CreateTableService createTableService;

    @RequestMapping(value = "/createTable/{tableName}",method = RequestMethod.POST)
    public AppResponse createTable(@PathVariable("tableName") String tableName, @RequestBody TableEntity tableEntity){
        AppResponse appResponse = createTableService.createTable(tableName,tableEntity.getColumns());
        return appResponse;
    }

    @RequestMapping(value = "/getFieldType")
    public AppResponse getCreateTableType(){
        AppResponse appResponse = createTableService.getFieldType();
        return appResponse;
    }


}

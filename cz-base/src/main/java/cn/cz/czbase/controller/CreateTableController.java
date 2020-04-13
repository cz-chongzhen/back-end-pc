package cn.cz.czbase.controller;

import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.SysTable;
import cn.cz.czbase.entity.SysTableField;
import cn.cz.czbase.service.CreateTableService;
import cn.cz.czbase.util.JedisUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/base-service/table")
public class CreateTableController {

    @Autowired
    private CreateTableService createTableService;

    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 在数据库中创建表  以及 修改表
     * @param tableEntity
     * @return
     */
    @RequestMapping(value = "/createTable", method = RequestMethod.POST)
    public AppResponse createTable(@RequestBody TableEntity tableEntity){
        return createTableService.createTable(tableEntity);
    }



    /**
     * 查询表
     * @return
     */
    @RequestMapping(value = "/queryTables",method = RequestMethod.GET)
    public AppResponse getTables(){
        return createTableService.getTables();
    }

    /**
     * 根据表信息查询对应字段信息
     * @param tableId
     * @return
     */
    @RequestMapping(value = "/queryTableField/{tableId}",method = RequestMethod.GET)
    public AppResponse getTableField(@PathVariable("tableId") Long tableId){
        return createTableService.getTableField(tableId);
    }

    @RequestMapping(value = "/generateId")
    public long generateId(){
        return jedisUtil.generateId();
    }

    @RequestMapping(value="/queryCommobox")
    public AppResponse queryCommobox(){
        return createTableService.queryCommobox();
    }

    @Data
    public static class TableEntity {
        private SysTable sysTable;
        private List<SysTableField> sysTableFieldList;
    }
}

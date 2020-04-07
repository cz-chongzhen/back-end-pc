package cn.cz.czbase.controller;

import cn.cz.czbase.config.CheckToken;
import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.SysTable;
import cn.cz.czbase.entity.SysTableField;
import cn.cz.czbase.service.CreateTableService;
import cn.cz.czbase.util.JedisUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/base-service/table")
public class CreateTableController {

    @Autowired
    private CreateTableService createTableService;

    /**
     * 在数据库中创建表
     * @param tableEntity
     * @return
     */
    @RequestMapping(value = "/createTable", method = RequestMethod.POST)
    public AppResponse createTable(@RequestBody TableEntity tableEntity){
        return createTableService.createTable(tableEntity);
    }

    @Data
    public static class TableEntity {
        private SysTable sysTable;
        private List<SysTableField> sysTableFieldList;
    }


}

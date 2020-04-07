package cn.cz.czbase.service.impl;

import cn.cz.czbase.controller.CreateTableController;
import cn.cz.czbase.dao.CreateTableDao;
import cn.cz.czbase.entity.*;
import cn.cz.czbase.service.CreateTableService;
import cn.cz.czbase.util.JedisUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateTableServiceImpl implements CreateTableService {

    @Autowired
    private CreateTableDao createTableDao;

    @Autowired
    private JedisUtil jedisUtil;



    public AppResponse addFields(List<SysTableField> fieldList) {
        DateTime now = new DateTime();
        if(fieldList!=null&&fieldList.size()>0){
            for(SysTableField field:fieldList){
                field.setCreateDateTime(now.toString("yyyy-MM-dd HH:mm:ss"));
                field.setId(jedisUtil.generateId());
            }
        }
        createTableDao.addSysTableField(fieldList);
        AppResponse appResponse = new AppResponse(200,"字段添加成功");
        return appResponse;
    }


    public SysTable addTableRecorder(SysTable sysTable) {
        DateTime now = new DateTime();
        sysTable.setId(jedisUtil.generateId());
        sysTable.setCreateDateTime(now.toString("yyyy-MM-dd HH:mm:ss"));
        List<SysTable> sysTableList = new ArrayList<>();
        sysTableList.add(sysTable);
        createTableDao.addTableRecorder(sysTableList);
        return sysTable;
    }


    public void createTableRecol(SysTable sysTable,List<SysTableField> sysTableFieldList) {
        createTableDao.createTable(sysTable.getTableName(),sysTableFieldList);
    }

    @Override
    public AppResponse createTable(CreateTableController.TableEntity tableEntity) {
        SysTable sysTable = tableEntity.getSysTable();
        boolean hasTableInSystem = hasTableByTableName(sysTable.getTableName());
        //查询数据库中是否有此表 若有直接抛出异常
        if(hasTableInSystem)
            throw new RuntimeException("该表已存在");
        //查看systable中是否已经存在此表的记录
        boolean hasTableInTable = hasTableInTableForm(sysTable.getTableName());
        if(!hasTableInTable)
            sysTable = addTableRecorder(sysTable);
        List<SysTableField> sysTableFieldList = tableEntity.getSysTableFieldList();
        if(sysTableFieldList!=null && sysTableFieldList.size()>0){
            for(SysTableField field:sysTableFieldList){
                field.setId(jedisUtil.generateId());
                field.setTableId(sysTable.getId());
            }
        }
        //如果字段表中已经存在要创建表的字段则直接删除
        deleteFieldsIfExist(sysTable.getId());

        //在字段表中存储要创建表的字段
        addFields(sysTableFieldList);

        //创建表
        createTableRecol(sysTable,sysTableFieldList);
        return new AppResponse(sysTable,200,"创建成功");
    }

    private boolean hasTableInTableForm(String tableName) {
        List list = createTableDao.queryHasTableInForm(tableName);
        if(list!=null && list.size()>0)
            return true;
        return false;
    }

    private void deleteFieldsIfExist(long tableId) {
        createTableDao.deleteFieldsIfExist(tableId);
    }

    public boolean hasTableByTableName(String tableName){
        List list = createTableDao.queryHasTableByTableName(tableName);
        if(list!=null && list.size()>0)
            return true;
        return false;
    }


}

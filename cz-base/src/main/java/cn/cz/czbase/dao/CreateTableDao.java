package cn.cz.czbase.dao;

import cn.cz.czbase.entity.Column;
import cn.cz.czbase.entity.SysTable;
import cn.cz.czbase.entity.SysTableField;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreateTableDao {
    int createTable(@Param("tableName") String tableName, @Param("columns")List<SysTableField> columns);

    int getFieldType();

    int addSysTableField(@Param("fieldList") List<SysTableField> fieldList);

    int addTableRecorder(@Param("sysTableList") List<SysTable> sysTableList);

    List queryHasTableByTableName(@Param("tableName") String tableName);

    void deleteFieldsIfExist(@Param("tableId") long tableId);

    List queryHasTableInForm(String tableName);

    List<SysTableField> queryFieldByTable(SysTableField sysTableField);

    void updateField(@Param("updateFieldList") List<SysTableField> updateFieldList, @Param("tableName") String tableName);

    void addField(@Param("addFieldList") List<SysTableField> addFieldList, String tableName);

    void updateTableRecorder(SysTable sysTable);

    List<SysTable> getTables();

    List queryCommobox();
}

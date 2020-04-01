package cn.cz.czbase.dao;

import cn.cz.czbase.entity.Column;
import cn.cz.czbase.entity.TableEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreateTableDao {
    int createTable(@Param("tableName") String tableName, @Param("columns")List<Column> columns);

    int getFieldType();
}

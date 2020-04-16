package cn.cz.czbase.dao;

import cn.cz.czbase.entity.CommonEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommonDAO {
    List<Map> queryTableData(CommonEntity commonEntity);

    void updateTableData(CommonEntity commonEntity);

    void deleteTableData(CommonEntity commonEntity);
}

package cn.cz.czbase.service.impl;

import cn.cz.czbase.dao.CreateTableDao;
import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.Column;
import cn.cz.czbase.service.CreateTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateTableServiceImpl implements CreateTableService {
    @Autowired
    private CreateTableDao createTableDao;

    @Override
    public AppResponse createTable(String name, List<Column> columnList) {
        int status = createTableDao.createTable(name,columnList);
        AppResponse appResponse = new AppResponse();
        appResponse.setMessage("创建成功");
        appResponse.setStatusCode(200);
        return appResponse;
    }

    @Override
    public AppResponse getFieldType() {
        int status = createTableDao.getFieldType();
        return null;
    }
}

package cn.cz.czbase.service.impl;

import cn.cz.czbase.dao.CommonDAO;
import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.CommonEntity;
import cn.cz.czbase.service.CommonService;
import cn.cz.czbase.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonDAO commonDAO;
    @Autowired
    private JedisUtil jedisUtil;
    @Override
    public AppResponse queryTableData(CommonEntity commonEntity) {
        List<Map> tableData = commonDAO.queryTableData(commonEntity);
        return new AppResponse(tableData,200,"查询成功");
    }

    @Override
    public AppResponse deleteData(CommonEntity commonEntity) {
        commonDAO.deleteTableData(commonEntity);
        return new AppResponse(null,200,"删除成功");
    }

    @Override
    public AppResponse updateTableData(CommonEntity commonEntity) {
        //使用LinkedHashMap保证每行数据中的列属性迭代的时候顺序一致
        List<LinkedHashMap<String,Object>> updateList = commonEntity.getUpdateList();
        for(LinkedHashMap<String,Object> map:updateList){
            if(!map.containsKey("id") || Long.parseLong(map.get("id").toString())==0){
                map.put("id",jedisUtil.generateId());
            }
        }
        LinkedHashMap<String,Object> field = updateList.get(0);
        commonEntity.setFieldList(field);
        commonDAO.updateTableData(commonEntity);
        return new AppResponse(null,200,"更新成功");
    }
}

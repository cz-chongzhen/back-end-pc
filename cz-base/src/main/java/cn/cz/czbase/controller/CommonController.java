package cn.cz.czbase.controller;

import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.CommonEntity;
import cn.cz.czbase.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base-service/common")
public class CommonController {
    @Autowired
    private CommonService commonService;

    /**
     * 通用查询方法
     * @param commonEntity
     * @return
     */
    @RequestMapping("/query")
    public AppResponse queryData(@RequestBody CommonEntity commonEntity){
        return commonService.queryTableData(commonEntity);
    }

    /**
     * 通用修改方法或者添加  根据行数据是否用主键 即id  用id则是修改否则是添加
     * @param commonEntity
     * @return
     */
    @RequestMapping("update")
    public AppResponse updateData(@RequestBody CommonEntity commonEntity){
        return commonService.updateTableData(commonEntity);
    }

    @RequestMapping("delete")
    public AppResponse deleteData(@RequestBody CommonEntity commonEntity){
        return commonService.deleteData(commonEntity);
    }

}

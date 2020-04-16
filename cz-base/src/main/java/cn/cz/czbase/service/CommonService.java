package cn.cz.czbase.service;

import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.CommonEntity;

public interface CommonService {
    AppResponse queryTableData(CommonEntity commonEntity);

    AppResponse updateTableData(CommonEntity commonEntity);

    AppResponse deleteData(CommonEntity commonEntity);
}

package cn.cz.czbase.service;

import cn.cz.czbase.entity.AppResponse;
import cn.cz.czbase.entity.CommonEntity;

import javax.servlet.http.HttpServletRequest;

public interface CommonService {
    AppResponse queryTableData(CommonEntity commonEntity);

    AppResponse updateTableData(CommonEntity commonEntity);

    AppResponse deleteData(CommonEntity commonEntity);

    AppResponse fileUpload(HttpServletRequest request);
}

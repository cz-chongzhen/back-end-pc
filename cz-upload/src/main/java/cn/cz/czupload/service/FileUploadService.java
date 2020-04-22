package cn.cz.czupload.service;

import cn.cz.czupload.entity.AppResponse;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {
    AppResponse fileUpload(HttpServletRequest request);
}

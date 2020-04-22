package cn.cz.czupload.controller;

import cn.cz.czupload.entity.AppResponse;
import cn.cz.czupload.entity.WebReqEntity;
import cn.cz.czupload.service.FileUploadService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cz-upload")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public AppResponse fileUpload(HttpServletRequest request){
        return fileUploadService.fileUpload(request);
    }
}

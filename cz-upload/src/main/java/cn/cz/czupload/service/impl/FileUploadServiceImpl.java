package cn.cz.czupload.service.impl;

import cn.cz.czupload.entity.AppResponse;
import cn.cz.czupload.service.FileUploadService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private static final String IMAGE_PATH = "/home/cz-chongzhen/images/";
    private static final String IMAGE_PATH_TEST = "D:\\images\\";
    @Override
    public AppResponse fileUpload(HttpServletRequest req) {
        if(ServletFileUpload.isMultipartContent(req)){
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
            try {
                //为文件生成新的文件名  防止名称重复
                String fileName = UUID.randomUUID().toString();
                req.setCharacterEncoding("UTF-8");
                //解析表单中的文件列表
                List<FileItem> fileItems = upload.parseRequest(req);
                Iterator<FileItem> iterator = fileItems.iterator();
                while (iterator.hasNext()){
                    FileItem fileItem = iterator.next();
                    if(!fileItem.isFormField()){
                        //文件名称
                        String oldFieldName = fileItem.getName();
                        fileName = fileName + oldFieldName.substring(oldFieldName.lastIndexOf("."),oldFieldName.length());
                        fileItem.write(new File(IMAGE_PATH_TEST+fileName));
                        return new AppResponse(IMAGE_PATH_TEST+fileName,200,"上传成功");
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }




}

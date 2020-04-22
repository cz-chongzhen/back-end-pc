package cn.cz.czupload.entity;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class WebReqEntity {
    private HttpServletRequest request;
}

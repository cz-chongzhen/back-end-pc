package cn.cz.czupload.entity;

import lombok.Data;

@Data
public class AppResponse {
    private Object appData;
    private int statusCode;
    private String message;

    /**
     * 无参构造方法
     */
    public AppResponse(){

    }

    /**
     *
     * @param o
     * @param statusCode
     * @param message
     */
    public AppResponse(Object o, int statusCode, String message){
        this.appData = o;
        this.statusCode = statusCode;
        this.message = message;
    }


    public AppResponse(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }
}

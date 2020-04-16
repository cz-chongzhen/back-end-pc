package cn.cz.czbase.exception;

import lombok.Data;

@Data
public class BaseLogicException extends RuntimeException {
    private static final long serialVersionUID = 3695156115567584160L;
    private Object appData;
    private int statusCode;
    private String message;

    public BaseLogicException(Object appData,int statusCode,String message){
        super(message);
        this.statusCode = statusCode;
        this.appData = appData;
    }

}

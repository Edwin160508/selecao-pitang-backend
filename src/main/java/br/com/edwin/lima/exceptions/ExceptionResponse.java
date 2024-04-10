package br.com.edwin.lima.exceptions;

import java.util.Date;
public class ExceptionResponse {

    private Integer errorCode;
    private Date timeStamp;
    private String message;
    private String detail;


    public ExceptionResponse(Integer errorCode, Date timeStamp, String message, String detail){
        this.errorCode = errorCode;
        this.timeStamp = timeStamp;
        this.message = message;
        this.detail = detail;

    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

}

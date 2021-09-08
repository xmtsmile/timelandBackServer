package com.study.note.common;

import java.io.Serializable;

/**
 * @author 王者奉
 * @date 2021/3/1 9:08
 */
public class BaseResponse implements Serializable {

    public static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";

    public static final String DEFAULT_ERROR_MESSAGE = "网络异常";

    public static final Integer DEFAULT_SUCCESS_CODE = 200;

    public static final Integer DEFAULT_ERROR_CODE = 500;

    public static final Integer DEFAULT_SUCCESS_PRE_CODE = 2;
    /**
     * 响应状态码
     */
    private Integer status = 200;

    /**
     * 响应描述信息
     */
    private String message;



    public BaseResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public  boolean judgeStatusStart2(){
        return (""+(this.getStatus()/100)).startsWith("2");
    }
}

package com.study.note.common;

/**
 * @author 王者奉
 * @date 2021/3/1 9:29
 */
public class SuccessDataResponse extends DataResponse{
    public SuccessDataResponse(Integer status, String message, Object data) {
        super(status, message, data);
    }
}
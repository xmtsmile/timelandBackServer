package com.study.note.common;

/**
 * @author 王者奉
 * @date 2021/3/1 9:28
 */
public class ErrorDataResponse extends DataResponse{
    public ErrorDataResponse(Integer status, String message, Object data) {
        super(status, message, data);
    }
}

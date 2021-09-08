package com.study.note.common;

import lombok.Data;

@Data
public class DataResponse<T> extends BaseResponse{
    private T data;

    public DataResponse() {
        super();
    }

    public DataResponse(Integer status, String message, T data) {
        super(status, message);
        this.data = data;
    }


    public ErrorDataResponse defaultErrorDataResponse() {
        return new ErrorDataResponse(500, DEFAULT_ERROR_MESSAGE, null);
    }

    public ErrorDataResponse defaultErrorDataResponse(T data) {
        return new ErrorDataResponse(500, DEFAULT_ERROR_MESSAGE, data);
    }

    public SuccessDataResponse defaultSuccessDataResponse() {
        return new SuccessDataResponse(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);
    }

    public SuccessDataResponse defaultSuccessDataResponse(T data) {
        return new SuccessDataResponse(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, data);
    }

    public SuccessDataResponse defaultOperationResponse(String message, T data) {
        return new SuccessDataResponse(DEFAULT_SUCCESS_CODE, message, data);
    }

    public SuccessDataResponse defaultOperationResponse(String message) {
        return defaultOperationResponse(message, null);
    }

    public SuccessDataResponse defaultSuccessOperationResponse() {
        return defaultOperationResponse("true", null);
    }
}

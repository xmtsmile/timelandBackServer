package com.study.note.common;

/**
 * @author 王者奉
 * @date 2021/3/1 9:39
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 获取异常的状态码
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     */
    String getMessage();
}

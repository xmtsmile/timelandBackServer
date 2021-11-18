package com.study.note.utils.token;

public interface TokenHelper {

    /**
     * 根据id生成token存入redis
     *
     */
    TokenModel create(String id);

    /**
     * 根据token查找id
     */
    String get(String token);
}

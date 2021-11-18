package com.study.note.utils.token;

import com.study.note.utils.redis.RedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RedisTokenHelp implements TokenHelper {

    @Autowired
    private RedisClient redisClient;

    @Override
    public TokenModel create(String id) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel mode = new TokenModel(id, token);
        redisClient.set(token, id == null ? null : String.valueOf(id), RedisClient.TOKEN_EXPIRES_SECOND);
        return mode;
    }

    @Override
    public String get(String token) {
        return redisClient.get(token);
    }
}

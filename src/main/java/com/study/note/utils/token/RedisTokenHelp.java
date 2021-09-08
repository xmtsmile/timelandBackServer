package com.study.note.utils.token;

import com.study.note.utils.redis.RedisClient;
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
        redisClient.set(id == null ? null : String.valueOf(id), token, RedisClient.TOKEN_EXPIRES_SECOND);
        return mode;
    }
}

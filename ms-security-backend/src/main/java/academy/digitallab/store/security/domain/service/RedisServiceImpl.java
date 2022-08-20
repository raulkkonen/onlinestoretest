package academy.digitallab.store.security.domain.service;


import academy.digitallab.store.security.auth.TokenAuthenticationService;
import academy.digitallab.store.security.domain.dto.TokenRedisDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RefreshScope
@Service
@Slf4j
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{


    private final RedisTemplate<String, Object> template;




    @Override
    public boolean existToken(String token) {
        Boolean exist = false;
        exist = template.hasKey(doTokenKeySession(token) );
        if (null == exist){
            return false;
        }
        return exist;
    }

    @Override
    public void addToken(String token) {
        TokenRedisDto tokenRedis = TokenAuthenticationService.getExpireToken(token);
        String key = doTokenKeySession(token);
        template.opsForValue().set(key, String.valueOf(tokenRedis.getUsername()));
        Long nowTime = new Date().getTime();
        long session=  tokenRedis.getDateTime() - nowTime;
        template.expire(key,session, TimeUnit.MILLISECONDS);
    }
    private static String  doTokenKeySession(String token){
        return "token:" + token;
    }
}

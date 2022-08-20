package academy.digitallab.store.customer.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;



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

    private static String  doTokenKeySession(String token){
        return "token:" + token;
    }
}

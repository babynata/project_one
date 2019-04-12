package project_one.shiro.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("redisCache")
public class RedisCacheUtil {

    @Resource(name = "redisTemplate")
    private StringRedisTemplate redisTemplate;

    public void hset(String key,String field,String value){
        if(null==key||"".equals(key)){
            return;
        }

        redisTemplate.opsForHash().put(key,field,value);
    }

    public String hget(String key,String field){
        if(null==key||"".equals(key)){
            return null;
        }

        return (String) redisTemplate.opsForHash().get(key,field);
    }

    public boolean hexists(String key,String field){
        if(null==key||"".equals(key)){
            return false;
        }

        return redisTemplate.opsForHash().hasKey(key,field);
    }

    public Long hsize(String key){
        if(null==key||"".equals(key)){
            return 0L;
        }

        return redisTemplate.opsForHash().size(key);
    }

    public void hdel(String key,String field){
        if(null==key||"".equals(key)){
            return;
        }

        redisTemplate.opsForHash().delete(key,field);
    }
}

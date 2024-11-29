package sg.ed.nus.iss.ssf_15w.repository;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.ed.nus.iss.ssf_15w.utilities.Redis;

@Repository
public class ContactRedisRepository {
    private static final Logger logger = Logger.getLogger(ContactRedisRepository.class.getName());

    @Qualifier(Redis.TEMPLATE)
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public Map<String, String> getAll(String key) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        Map<String, String> map = hashOps.entries(key);

        return map;
    }

    public void add(String key, String element) {
        String[] values = element.split(",");
        redisTemplate.opsForHash().put(key, values[0], element);
    }

    public String findById(String key, String id) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        
        return hashOps.get(key, id);
    }
}

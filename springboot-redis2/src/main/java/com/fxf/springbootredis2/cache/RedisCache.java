package com.fxf.springbootredis2.cache;

import com.fxf.springbootredis2.utils.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.ReadWriteLock;

public class RedisCache implements Cache {

    private final String id;

    // 必须存在构造方法
    public RedisCache(String id) {
        // id 就是当前放入缓存的mapper的namespace ---> com.zy.dao.UserDao
        System.out.println("id =============> " + id);
        this.id = id;
    }

//    返回cache的唯一标识
    @Override
    public String getId() {
        return this.id;
    }

//      使用RedisTemplate来往缓存中存值
    @Override
    public void putObject(Object key, Object value) {

        // 使用hash类型作为缓存存储模型 <key, <hashkey, value>>  ==> <namespace <当前方法的key, 返回值>>
        getRedisTemplate().opsForHash().put(id, getKeyMD5(key.toString()), value);

    }

    @Override
    public Object getObject(Object key) {
        System.out.println("key = " + key);


        // 根据key从redis的hash类型中key获取数据
        return getRedisTemplate().opsForHash().get(id, getKeyMD5(key.toString()));
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {
        System.out.println("清空缓存");
        getRedisTemplate().delete(id);
    }

//   用来计算缓存数量
    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id.toString()).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    ////使用ApplicationContextUtils来获取redisTemplate，封装redisTemplate
    private RedisTemplate getRedisTemplate(){
        // 通过ApplicationContextUtils来获取redisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

//    通过Spring内置的MD5工具类对key值进行加密来对内存优化
    private String getKeyMD5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}

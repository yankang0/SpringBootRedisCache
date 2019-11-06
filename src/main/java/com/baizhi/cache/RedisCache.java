package com.baizhi.cache;

import com.baizhi.util.SpringContextUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {
    private String id;

    private  final ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        System.out.println("id-------namespace------"+id);
        return id;
    }
    /*
    * 把数据放入缓存中
    * */
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("key---------"+key);
        System.out.println("value--------"+value);
        //获取RedisTemplate对象
        RedisTemplate redisTemplate =(RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        //把对应的数据 放入缓存中
        redisTemplate.opsForHash().put(id,key,value);

    }
    /*
    * 从缓存中获取数据
    * */
    @Override
    public Object getObject(Object key) {
        //获取RedisTemplate对象
        RedisTemplate redisTemplate =(RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        Object o = redisTemplate.opsForHash().get(id, key);

        return o;
    }
    /*
    * 当发生增删改时需要清空缓存
    * */
    @Override
    public Object removeObject(Object key) {
        //获取RedisTemplate对象
        RedisTemplate redisTemplate =(RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        redisTemplate.opsForHash().delete(id,key);
        return null;
    }
    /*
    * 当发生增删改时需要清空缓存
    * */
    @Override
    public void clear() {
        //获取RedisTemplate对象
        RedisTemplate redisTemplate =(RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        redisTemplate.delete(id);
    }
    /*
    * 返回长度
    * */
    @Override
    public int getSize() {
        //获取RedisTemplate对象
        RedisTemplate redisTemplate =(RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        Long size = redisTemplate.opsForHash().size(id);
        return size.intValue();
    }
    /*
    * 读写锁     多个线程拥有读锁  只有一个线程拥有写锁
    * */
    @Override
    public ReadWriteLock getReadWriteLock() {
        return reentrantReadWriteLock;
    }
}

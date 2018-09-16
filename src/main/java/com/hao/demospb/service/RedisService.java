package com.hao.demospb.service;

/**
 * @author {USER}
 * @create 2018/8/12
 */
public interface RedisService {
    void set(String key, String value);

    String get(String key);
}

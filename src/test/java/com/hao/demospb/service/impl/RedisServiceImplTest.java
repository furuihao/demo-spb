package com.hao.demospb.service.impl;

import com.hao.demospb.service.RedisService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * RedisServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre> 08 ,12, 2018</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceImplTest {

    @Resource
    private RedisService redisService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: set(String key, String value)
     */
    @Test
    public void testSet() throws Exception {
        redisService.set("name","123456");
    }


} 

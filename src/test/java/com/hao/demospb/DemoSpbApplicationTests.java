package com.hao.demospb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpbApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test(){
		Jedis jedis = new Jedis("192.168.168.137",6380);
        jedis.set("name", "kk");
        jedis.rpush("mylist","1","2","3");

        new Jedis();
	}

}

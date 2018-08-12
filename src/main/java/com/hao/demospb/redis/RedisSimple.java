package com.hao.demospb.redis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisSimple {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.168.137");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
        //nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
        //expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒
        jedis.set("mykey","ss","nx","ex",1000);// ex 表示秒 ，px代表毫秒

        //如果一个key设置了过期时间，则取消其过期时间，使其永久存在,返回1代表取消过期时间成功，0代表不成功
        jedis.persist("foo");

        //为key设置一个特定的过期时间，单位为秒
        jedis.expire("foo",20);

        //expireAt设置的时间不是能存活多久，而是固定的UNIX时间
        jedis.expireAt("foo",System.currentTimeMillis());

        //只有当key已经存在时才进行set
        jedis.setex("foo",10,"bar");

        //只有当key不存在是才进行set
        jedis.setnx("foo","bar");

        //判断指定的key是否存在
        boolean exists = jedis.exists("foo");

        //将指定Key的值加1 ，返回增加后的新值
        long result = jedis.incr("counter");
        //将指定的key的值增加指定的值
        result = jedis.incrBy("counter",10);
        //将指定Key的值减1
        result = jedis.decr("counter");
        result = jedis.decrBy("counter",10);

        //若key存在，将value追加到原有字符串的末尾。若key不存在，则创建一个新的空字符串
        jedis.append("foo","appendtest");

//////////////////////////////////////////////////hash////////////////////////////////////////////////////////////////
        //如果该字段已经存在，那么将会更新该字段的值，返回0.如果字段不存在，则新创建一个并且返回1
        jedis.hset("user","id","1");

        //如果该key对应的值是一个Hash表，则返回对应字段的值。 如果不存在该字段，或者key不存在，则返回一个"nil"值
        String userId = jedis.hget("user","id");

        //如果该字段已经存在，则返回0.若字段不存在，则创建后set，返回1
        jedis.hsetnx("user","id","1");

        Map<String,String> map = new HashMap();
        map.put("id","2");
        map.put("name","jack");
        //设置成功返回OK，设置不成功则返回EXCEPTION
        jedis.hmset("user",map);

        //按顺序返回多个字段的值。
        List<String> users  = jedis.hmget("user","id","name");
        //判断hash中指定字段是否存在
        jedis.hexists("user","name");

        //返回 key 指定的哈希集中所有的字段和值
        Map<String, String>  map1 = jedis.hgetAll("user");

        //删除成功返回1， 删除不成功返回0
        result = jedis.hdel("user","id","name");
 //////////////////////////////////////////////////list////////////////////////////////////////////////////////////////
        //向存于 key 的列表的尾部插入所有指定的值。如果 key 不存在，那么会创建一个空的列表然后再进行 push 操作,返回值：操作后的列表长度
        jedis.rpush("mylist","1","2","3");
        //将所有指定的值插入到存于 key 的列表的头部。如果 key 不存在，那么在进行 push 操作前会创建一个空列表 再进行 push 操作 返回值：操作后的列表长度
        jedis.lpush("mylist","a","b");
        //返回存储在 key 里的list的长度。 如果 key 不存在，那么就被看作是空list，并且返回长度为 0。
        jedis.llen("mylist");
        //返回存储在 key 的列表里指定范围内的元素。 start 和 end 偏移量都是基于0的下标，即list的第一个元素下标是0（list的表头），第二个元素下标是1，以此类推
        List<String> list = jedis.lrange("mylist",1,3);
        //返回列表里的元素的索引 index 存储在 key 里面。 下标是从0开始索引的，所以 0 是表示第一个元素
        value = jedis.lindex("mylist",2);

        //设置 index 位置的list元素的值为 value
        jedis.lset("mylist",2,"33");
        //移除并且返回 key 对应的 list 的第一个元素 当 key 不存在时返回 nil
        value = jedis.lpop("mylist");
        //移除并且返回 key 对应的 list 的最后一个元素 当 key 不存在时返回 nil
        value = jedis.rpop("mylist");
//////////////////////////////////////////////////set////////////////////////////////////////////////////////////////

        /* * 添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略.如果集合key
         * 不存在，则新建集合key,并添加member元素到集合key中.
*/
        jedis.sadd("myset","a","b","c","d");
        //返回key集合所有的元素.
        Set<String> set = jedis.smembers("myset");
        //  在key集合中移除指定的元素，返回从集合中移除元素的个数，不包括不存在的成员
        long num = jedis.srem("myset","c","d");
        //移除并返回一个集合中的随机元素,返回被移除的元素, 当key不存在的时候返回 nil
        jedis.spop("myset");
        boolean b = jedis.sismember("myset","a");
//////////////////////////////////////////////////zset////////////////////////////////////////////////////////////////
  /*      *
         * 该命令添加指定的成员到key对应的有序集合中，每个成员都有一个分数。你可以指定多个分数/成员组合。如果一个指定的成员已经在对应的有序集合中了，
         * 那么其分数就会被更新成最新的
         * ，并且该成员会重新调整到正确的位置，以确保集合有序。如果key不存在，就会创建一个含有这些成员的有序集合，就好像往一个空的集合中添加一样
*/
        jedis.zadd("myzset",2,"a");

        Map<String,Double> scoreMap = new HashMap<String, Double>();
        scoreMap.put("b",1D);
        scoreMap.put("c",3D);
        scoreMap.put("d",5D);
        jedis.zadd("myzset",scoreMap);
        //返回有序集key中，指定区间内的成员。其中成员按score值递增(从小到大)来排序。具有相同score值的成员按字典序来排列
        set = jedis.zrange("myzset",0,2);
        //从集合中删除指定member元素,回的是从有序集合中删除的成员个数，不包括不存在的成员
        result = jedis.zrem("myzset","a","b");
       /* *
         * 为有序集key的成员member的score值加上增量increment。如果key中不存在member，就在key中添加一个member，
         * score是increment（就好像它之前的score是0.0）。如果key不存在，就创建一个只含有指定member成员的有序集合
*/
        jedis.zincrby("myzset",10,"c");
/*

        *
         * 返回有序集key中成员member的排名。其中有序集成员按score值递增(从小到大)顺序排列。排名以0为底，也就是说，
         * score值最小的成员排名为0。
*/

        result = jedis.zrank("myzset","c");
        //返回从大道小的排名
        result = jedis.zrevrank("myzset","c");




    }
}

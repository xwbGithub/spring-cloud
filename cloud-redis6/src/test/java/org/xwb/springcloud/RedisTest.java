/*
package org.xwb.springcloud;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisTest {
    private final static String ip="192.168.68.133";

    public static void main(String[] args) {
        Jedis jedis = new Jedis(ip, 6379);
        String value = jedis.ping();
        System.out.println(value);
    }

    @Test
    public void demo1() {
        Jedis jedis = new Jedis(ip, 6379);
        String value = jedis.ping();
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size());
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(jedis.exists("k1"));
        System.out.println(jedis.ttl("k1"));
        System.out.println(jedis.get("k1"));

    }

    @Test
    public void List() {
        Jedis jedis = new Jedis(ip, 6379);
        jedis.rpush("myList", "12", "23", "34", "45", "56", "67");
        List<String> list = jedis.lrange("myList", 0, -1);
        for (String element : list) {
            System.out.println(element);
        }
    }

    @Test
    public void set() {
        Jedis jedis = new Jedis(ip, 6379);
        jedis.sadd("orders", "order01");
        jedis.sadd("orders", "order02");
        jedis.sadd("orders", "order03");
        jedis.sadd("orders", "order04");

        Set<String> smembers = jedis.smembers("orders");
        for (String order : smembers) {
            System.out.println(order);
        }
        jedis.srem("orders", "order02");

    }

    @Test
    public void hash() {
        Jedis jedis = new Jedis(ip, 6379);
        jedis.hset("hash1", "userName", "lisi");
        System.out.println(jedis.hget("hash1", "userName"));
        Map<String, String> map = new HashMap<String, String>();
        map.put("telphone", "13810169999");
        map.put("address", "atguigu");
        map.put("email", "abc@163.com");
        jedis.hmset("hash2", map);
        List<String> result = jedis.hmget("hash2", "telphone", "email");
        for (String element : result) {
            System.out.println(element);
        }
    }

    @Test
    public void zset() {
        Jedis jedis = new Jedis(ip, 6379);
        jedis.zadd("zset01", 100d, "z3");
        jedis.zadd("zset01", 90d, "l4");
        jedis.zadd("zset01", 80d, "w5");
        jedis.zadd("zset01", 70d, "z6");

        Set<String> zrange = jedis.zrange("zset01", 0, -1);
        for (String e : zrange) {
            System.out.println(e);
        }
    }
}
*/

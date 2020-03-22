package cn.cz.czbase.redis;

import cn.cz.czbase.util.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTestDemo {
    @Autowired
    private JedisUtil jedisUtil;
    @Test
    public void testDemo1(){
        String str = jedisUtil.get("id");
        System.out.println(str);
    }
}

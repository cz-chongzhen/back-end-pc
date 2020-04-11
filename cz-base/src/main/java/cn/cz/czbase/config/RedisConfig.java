package cn.cz.czbase.config;

import cn.cz.czbase.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.jedis.pool.max-active}")
    private int maxTotal;

    @Value("${redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.timeout}")
    private int timeOut;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;


    @Bean
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }


    @Bean
    public JedisPool getJedisPool(){
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,hostName, port, timeOut, password);
        return jedisPool;
    }

    @Bean
    public JedisUtil getJedisUtil(){
        return new JedisUtil();
    }

}

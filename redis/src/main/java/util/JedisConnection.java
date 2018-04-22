package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author dingshuangkun
 * @date on 2018/4/21.
 */
public class JedisConnection {

    private String address;

    private int port;

    private String auth;

    private static final int MAX_ACTIVE = 1024;

    private static final int MAX_IDLE = 200;

    private static final int MAX_WAIT = 10000;

    private static final int TIME_OUT = 10000;

    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    public JedisConnection(String address, int port) {
        this(address, port, null);
    }

    public JedisConnection(String address, int port, String auth) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        jedisPool = new JedisPool(config, address, port, TIME_OUT, auth);
    }

    /**
     * 获取链接
     *
     * @return
     */
    public Jedis getJedis() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        }
        return null;
    }

    /**
     * 关闭链接
     */
    public void closeResouces() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }
}

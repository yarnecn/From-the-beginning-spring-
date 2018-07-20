package cn.yarne.com.base.util;

import com.alibaba.druid.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis 工具类
 */
public class RedisUtil {

    protected static ReentrantLock lockPool = new ReentrantLock();

    // Redis服务器IP
    private static String HOST = "127.0.0.1";
    // Redis的端口号
    private static int PORT = 6379;
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 200;
    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 300;
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 300;
    // 超时时间
    private static int TIMEOUT = 2000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = false;
    public static JedisPool jedisPool = null;

    /**
     * 加载redisPool的配置信息
     */
   /* static{
        try {
            InputStream stream = RedisUtil.class.getClassLoader().getResourceAsStream("redis.properties");
            Properties prop = new Properties();
            prop.load(stream);
            HOST = prop.getProperty("redis.host");
            PORT = Integer.parseInt(prop.getProperty("redis.port"));
            TIMEOUT = Integer.parseInt(prop.getProperty("redis.timeOut"));
            MAX_WAIT = Integer.parseInt(prop.getProperty("redis.maxWaitMillis"));
            MAX_ACTIVE = Integer.parseInt(prop.getProperty("redis.maxActive"));
            MAX_IDLE = Integer.parseInt(prop.getProperty("redis.maxIdle"));
            TEST_ON_BORROW = Boolean.parseBoolean(prop.getProperty("redis.testOnBorrow"));
        } catch (Exception e) {
            System.out.println("解析配置文件失败！");
        }
    }*/

    /**
     * 初始化Redis连接池
     */
    private static void initialPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT);
        } catch (Exception e) {
            System.out.println("初始化连接池失败！");
        }
    }

    /**
     * 在多线程环境同步初始化，
     * redisPool只要一个就好了，所以要设置锁，保证只能初始化一个
     */
    private static void poolInit() {
        lockPool.lock();
        try {
            if (jedisPool == null) {
//                initialConfig();
                initialPool();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockPool.unlock();
        }
    }


    /**
     * 这里可以使用多线程进行加载，所以将锁去掉,
     * 保证了只有一个jedisPool，
     * @return
     */
    public static Jedis getJedis() {
        if (jedisPool == null) {
            poolInit();
        }
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            System.out.println("getJedis() 方法出错：" + e.getMessage());
        } finally {
            close(jedis);
        }
        return jedis;
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void close(final Jedis jedis) {
        try {
            if (jedis != null && jedisPool != null) {
                jedis.close();
            }
        } catch (Exception e) {
            System.out.println("close() 方法出错：" + e.getMessage());
        }
    }

    /**
     * 设置 String
     *
     * @param key
     * @param value
     */
    public static boolean setString(String key, String value) {
        try {
            getJedis().set(key, value);
            return true;
        } catch (Exception e) {
            System.out.println("setString() 方法出错：" + e.getMessage() + "key = " + key + "value = " + value);
            return false;
        }
    }

    /**
     * 设置 过期时间
     *
     * @param key
     * @param seconds
     *            以秒为单位
     * @param value
     */
    public static boolean setString(String key, int seconds, String value) {
        try {
            value = StringUtils.isEmpty(value) ? "" : value;
            getJedis().setex(key, seconds, value);
            return true;
        } catch (Exception e) {
            System.out.println("setString() 方法出错：" + e.getMessage());
            return false;
        }
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public static String getString(String key) {
        try {
            return getJedis().get(key);
        } catch (Exception e) {
            System.out.println("getString() 方法出错：" + e.getMessage() + "key = " + key);
            return "0$0";
        }
    }

    /**
     * 删除key的键值对
     * @param key
     * @return
     */
    public static boolean delString(String key){
        try {
            getJedis().del(key);
            return true;
        } catch (Exception e) {
            System.out.println("getString() 方法出错：" + e.getMessage() + "key = " + key);
            return false;
        }
    }
}
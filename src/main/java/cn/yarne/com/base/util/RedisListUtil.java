package cn.yarne.com.base.util;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.apache.shiro.crypto.hash.Hash;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

import java.util.List;


public class RedisListUtil {

    /**缓存生存时间 */
    private final int expire = 60000;
    /** 对存储结构为String类型的操作 */
    public Strings STRINGS;
    /** 对存储结构为List类型的操作 */
    public Lists LISTS;
    /** 对存储结构为Set类型的操作 */
    public Sets SETS;
    /** 对存储结构为HashMap类型的操作 */
    public Hash HASH;


    public Jedis getJedis() {
        return RedisUtil.getJedis();
    }

    /**
     * 根据key获取记录
     * @param key
     * @return
     */
    public String get(String key) {
            Jedis sjedis = getJedis();
            String value = sjedis.get(key);
            return value;
        }

        /**
         * 根据key获取记录
         * @param key
         * @return 值
         * */
        public byte[] get(byte[] key) {
            Jedis sjedis = getJedis();
            byte[] value = sjedis.get(key);
            return value;
        }

        /**
         * 添加有过期时间的记录
         *
         * @param  key
         * @param seconds 过期时间，以秒为单位
         * @param  value
         * @return String 操作状态
         * */
        public String setEx(String key, int seconds, String value) {
            Jedis jedis = getJedis();
            String str = jedis.setex(key, seconds, value);

            return str;
        }

        /**
         * 添加有过期时间的记录
         *
         * @param  key
         * @param seconds 过期时间，以秒为单位
         * @param  value
         * @return String 操作状态
         * */
        public String setEx(byte[] key, int seconds, byte[] value) {
            Jedis jedis = getJedis();
            String str = jedis.setex(key, seconds, value);
            return str;
        }

        /**
         * 添加一条记录，仅当给定的key不存在时才插入
         * @param  key
         * @param  value
         * @return long 状态码，1插入成功且key不存在，0未插入，key存在
         * */
        public long setnx(String key, String value) {
            Jedis jedis = getJedis();
            long str = jedis.setnx(key, value);

            return str;
        }

        /**
         * 添加记录,如果记录已存在将覆盖原有的value
         * @param  key
         * @param  value
         * @return 状态码
         * */
        public String set(String key, String value) {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        /**
         * 添加记录,如果记录已存在将覆盖原有的value
         * @param  key
         * @param  value
         * @return 状态码
         * */
        public String set(String key, byte[] value) {
            return set(SafeEncoder.encode(key), value);
        }

        /**
         * 添加记录,如果记录已存在将覆盖原有的value
         * @param  key
         * @param  value
         * @return 状态码
         * */
        public String set(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            return status;
        }

        /**
         * 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
         * 例:String str1="123456789";<br/>
         * 对str1操作后setRange(key,4,0000)，str1="123400009";
         * @param  key
         * @param offset
         * @param  value
         * @return long value的长度
         * */
        public long setRange(String key, long offset, String value) {
            Jedis jedis = getJedis();
            long len = jedis.setrange(key, offset, value);

            return len;
        }

        /**
         * 在指定的key中追加value
         * @param  key
         * @param  value
         * @return long 追加后value的长度
         * **/
        public long append(String key, String value) {
            Jedis jedis = getJedis();
            long len = jedis.append(key, value);
            return len;
        }

        /**
         * 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用
         * @param  key
         * @param number 要减去的值
         * @return long 减指定值后的值
         * */
        public long decrBy(String key, long number) {
            Jedis jedis = getJedis();
            long len = jedis.decrBy(key, number);

            return len;
        }

        /**
         * <b>可以作为获取唯一id的方法</b><br/>
         * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用
         * @param key
         * @param  number 要减去的值
         * @return long 相加后的值
         * */
        public long incrBy(String key, long number) {
            Jedis jedis = getJedis();
            long len = jedis.incrBy(key, number);
            return len;
        }

        /**
         * 对指定key对应的value进行截取
         * @param  key
         * @param startOffset 开始位置(包含)
         * @param endOffset 结束位置(包含)
         * @return String 截取的值
         * */
        public String getrange(String key, long startOffset, long endOffset) {
            //ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            String value = sjedis.getrange(key, startOffset, endOffset);
            return value;
        }

        /**
         * 获取并设置指定key对应的value<br/>
         * 如果key存在返回之前的value,否则返回null
         * @param  key
         * @param value
         * @return String 原始value或null
         * */
        public String getSet(String key, String value) {
            Jedis jedis = getJedis();
            String str = jedis.getSet(key, value);

            return str;
        }

        /**
         * 批量获取记录,如果指定的key不存在返回List的对应位置将是null
         * @param  keys
         * @return List<String> 值得集合
         * */
        public List<String> mget(String... keys) {
            Jedis jedis = getJedis();
            List<String> str = jedis.mget(keys);
            return str;
        }

        /**
         * 批量存储记录
         * @param keysvalues 例:keysvalues="key1","value1","key2","value2";
         * @return String 状态码
         * */
        public String mset(String... keysvalues) {
            Jedis jedis = getJedis();
            String str = jedis.mset(keysvalues);
            return str;
        }

        /**
         * 获取key对应的值的长度
         * @param  key
         * @return value值得长度
         * */
        public long strlen(String key) {
            Jedis jedis = getJedis();
            long len = jedis.strlen(key);

            return len;
        }

    //*******************************************Lists*******************************************//
    public class Lists {
        /**
         * List长度
         * @param key
         * @return 长度
         * */
        public long llen(String key) {
            return llen(SafeEncoder.encode(key));
        }

        /**
         * List长度
         * @param  key
         * @return 长度
         * */
        public long llen(byte[] key) {
            //ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            long count = sjedis.llen(key);
            return count;
        }

        /**
         * 覆盖操作,将覆盖List中指定位置的值
         * @param key
         * @param  index 位置
         * @param value 值
         * @return 状态码
         * */
        public String lset(byte[] key, int index, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.lset(key, index, value);
            return status;
        }

        /**
         * 覆盖操作,将覆盖List中指定位置的值
         * @param key
         * @param index 位置
         * @param  value 值
         * @return 状态码
         * */
        public String lset(String key, int index, String value) {
            return lset(SafeEncoder.encode(key), index,
                    SafeEncoder.encode(value));
        }

        /**
         * 在value的相对位置插入记录
         * @param key
         * @param where  前面插入或后面插入
         * @param  pivot 相对位置的内容
         * @param value 插入的内容
         * @return 记录总数
         * */
        public long linsert(String key, LIST_POSITION where, String pivot,
                            String value) {
            return linsert(SafeEncoder.encode(key), where,
                    SafeEncoder.encode(pivot), SafeEncoder.encode(value));
        }

        /**
         * 在指定位置插入记录
         * @param  key
         * @param where 前面插入或后面插入
         * @param  pivot 相对位置的内容
         * @param value 插入的内容
         * @return 记录总数
         * */
        public long linsert(byte[] key, LIST_POSITION where, byte[] pivot,
                            byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.linsert(key, where, pivot, value);
            return count;
        }

        /**
         * 获取List中指定位置的值
         * @param key
         * @param  index 位置
         * @return 值
         * **/
        public String lindex(String key, int index) {
            return SafeEncoder.encode(lindex(SafeEncoder.encode(key), index));
        }

        /**
         * 获取List中指定位置的值
         * @param key
         * @param  index 位置
         * @return 值
         * **/
        public byte[] lindex(byte[] key, int index) {
            //ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            byte[] value = sjedis.lindex(key, index);
            return value;
        }

        /**
         * 将List中的第一条记录移出List
         * @param key
         * @return 移出的记录
         * */
        public String lpop(String key) {
            return SafeEncoder.encode(lpop(SafeEncoder.encode(key)));
        }

        /**
         * 将List中的第一条记录移出List
         * @param key
         * @return 移出的记录
         * */
        public byte[] lpop(byte[] key) {
            Jedis jedis = getJedis();
            byte[] value = jedis.lpop(key);
            return value;
        }

        /**
         * 将List中最后第一条记录移出List
         *
         * @param  key
         * @return 移出的记录
         * */
        public String rpop(String key) {
            Jedis jedis = getJedis();
            String value = jedis.rpop(key);
            return value;
        }

        /**
         * 向List尾部追加记录
         * @param  key
         * @param value
         * @return 记录总数
         * */
        public long lpush(String key, String value) {
            return lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        /**
         * 向List头部追加记录
         * @param key
         * @param  value
         * @return 记录总数
         * */
        public long rpush(String key, String value) {
            Jedis jedis = getJedis();
            long count = jedis.rpush(key, value);
            return count;
        }

        /**
         * 向List头部追加记录
         * @param key
         * @param value
         * @return 记录总数
         * */
        public long rpush(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.rpush(key, value);
            return count;
        }

        /**
         * 向List中追加记录
         * @param key
         * @param   value
         * @return 记录总数
         * */
        public long lpush(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.lpush(key, value);
            return count;
        }

        /**
         * 获取指定范围的记录，可以做为分页使用
         * @param  key
         * @param  start
         * @param  end
         * @return List
         * */
        public List<String> lrange(String key, long start, long end) {
            //ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<String> list = sjedis.lrange(key, start, end);
            return list;
        }

        /**
         * 获取指定范围的记录，可以做为分页使用
         * @param key
         * @param start
         * @param  end 如果为负数，则尾部开始计算
         * @return List
         * */
        public List<byte[]> lrange(byte[] key, int start, int end) {
            //ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<byte[]> list = sjedis.lrange(key, start, end);
            return list;
        }

        /**
         * 删除List中c条记录，被删除的记录值为value
         * @param  key
         * @param c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
         * @param value 要匹配的值
         * @return 删除后的List中的记录数
         * */
        public long lrem(byte[] key, int c, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.lrem(key, c, value);
            return count;
        }

        /**
         * 删除List中c条记录，被删除的记录值为value
         * @param  key
         * @param  c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
         * @param value 要匹配的值
         * @return 删除后的List中的记录数
         * */
        public long lrem(String key, int c, String value) {
            return lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
        }

        /**
         * 算是删除吧，只保留start与end之间的记录
         * @param  key
         * @param  start 记录的开始位置(0表示第一条记录)
         * @param  end 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
         * @return 执行状态码
         * */
        public String ltrim(byte[] key, int start, int end) {
            Jedis jedis = getJedis();
            String str = jedis.ltrim(key, start, end);
            return str;
        }

        /**
         * 算是删除吧，只保留start与end之间的记录
         * @param key
         * @param  start 记录的开始位置(0表示第一条记录)
         * @param end 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
         * @return 执行状态码
         * */
        public String ltrim(String key, int start, int end) {
            return ltrim(SafeEncoder.encode(key), start, end);
        }
    }

}
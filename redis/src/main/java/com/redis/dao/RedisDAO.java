package com.redis.dao;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author dingshuangkun
 * @date on 2018/4/22.
 */
public class RedisDAO {

    // 分隔符
    private final static String SPLIT_MARK = ":";
    // 排序key里面的标记位
    private final static String SORT = "sort";
    // 主键key里面的标记位
    private final static String INDEX = "index";
    // list格式存放log的sql
    public final static String LOG = "log";
    // pub/sub模式打印log
    public final static String PUB_LOG = "publog";

    private Jedis jedis;

    private Transaction transaction;

    private Pipeline pipeline;

    public RedisDAO(Transaction transaction) {
        this.transaction = transaction;
    }

    public RedisDAO(Jedis jedis) {
        this.jedis = jedis;
    }

    public RedisDAO(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Boolean isExitKey(String key) {
        return jedis.get(key) == null ? false : true;
    }

    public static String get(String key, Jedis jedis) {
        return jedis.get(key);
    }


    public void set(String key, String value) {
        transaction.set(key, value);
    }

    /**
     * 存放set类型
     *
     * @param key
     * @param value
     */
    public void sadd(String key, String value) {
        transaction.sadd(key, value);
    }

    /**
     * 存放sortSet类型
     *
     * @param key
     * @param score
     * @param member
     */
    public void zadd(String key, double score, String member) {
        transaction.zadd(key, score, member);
    }

    /**
     * 存放list类型
     *
     * @param key
     * @param value
     */
    public void lpush(String key, String value) {
        transaction.lpush(key, value);
    }

    /**
     * @param key   key值 Note:sort:noteId 0 -1
     * @param start 初始位置
     * @param end   结束位置
     * @param jedis
     * @Description: 返回有序集 key 中，指定区间内的成员。 按照从小到大排序
     * @return:Set<String>
     */
    public static Set<String> getRangeSortSet(String key, int start, int end, Jedis jedis) {
        return jedis.zrange(key, start, end);
    }

    /**
     * @param key   key值 Note:sort:noteId 0 0
     * @param start 初始位置
     * @param end   结束位置
     * @param jedis
     * @Description: 返回有序集 key 中，指定区间内的成员。 按照从大到小排序
     * @see: 例如用来插入数据时候获取主键的id
     * @return:Set<String>
     */
    public static Set<String> getRevrangeSortSet(String key, int start, int end, Jedis jedis) {
        return jedis.zrevrange(key, start, end);
    }

    /**
     * 批量查询
     *
     * @param sortKey
     * @param jedis
     * @return
     */
    public static List<String> getListString(Set<String> sortKey, Jedis jedis) {
        List<String> list = new ArrayList<>();
        for (String key : sortKey) {
            list.add(jedis.get(key));
        }
        return list;
    }

    /**
     * @param key
     * @Description: 删除string类型数据
     */
    public void delString(String key) {
        transaction.del(key);
    }

    /**
     * @param key
     * @Description: 删除set类型数据
     */
    public void delSet(String key, String member) {
        transaction.srem(key, member);
    }

    /**
     * @param key
     * @param member
     * @Description: 删除sortset类型的数据
     */
    public void delSortSet(String key, String member) {
        transaction.zrem(key, member);
    }

    /**
     * 模糊查询key的值
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return jedis.keys(pattern);
    }

    /**
     * @param key
     * @Description:根据key值返回set集合
     * @return:Set<String>
     */
    public Set<String> smembers(String key) {
        return jedis.smembers(key);
    }

    /**
     * @param key
     * @Description:根据多个key值返回交集的id
     * @return:Set<String>
     */
    public Set<String> sinter(String... key) {
        return jedis.sinter(key);
    }

    /**
     * @param key
     * @Description:根据多个key值 返回并集的id
     * @return:Set<String>
     */
    public Set<String> sunion(String... key) {
        return jedis.sunion(key);
    }


    /**
     * @param tableName 表映射的类名
     * @param id        主键ID值
     * @param column    表映射的列信息
     * @param value     列对应的值
     * @Description:初始化将表中数据放到redis,存放格式为tableName:id:column.
     */
    public void setTable(String tableName, String id, String column, String value) {
        transaction.set(tableName + SPLIT_MARK + id + SPLIT_MARK + column, value);
    }

    /**
     * @param tableName
     * @param column
     * @param columnValue
     * @param id
     * @Description:初始化存放表中所有字段数据，存放格式为tableName:column:columnValue
     */
    public void saddColumn(String tableName, String column, String columnValue, String id) {
        transaction.sadd(tableName + SPLIT_MARK + column + SPLIT_MARK + columnValue, tableName + SPLIT_MARK + id);
    }

    /**
     * @param tableName 表映射的类名
     * @param id        主键id值
     * @param json      改id值对应的json字符串
     * @Description:初始化将表中数据放到redis,将bean转换成json格式，存放格式为tableName:id。
     */
    public void setJson(String tableName, String id, String json) {
        transaction.set(tableName + SPLIT_MARK + id, json);
    }

    /**
     * @param
     * @Description: 根据jsonKey，获取相应的json字符串，转换成实体类List
     * @return:List<T>
     */
    public static List<?> getListBean(Set<String> sortKey, Class cls, Jedis jedis) {
        List<Object> list = new ArrayList<>();
        for (String key : sortKey) {
            list.add(getBean(key, cls, jedis));
        }
        return list;
    }


    private static Object getBean(String key, Class cls, Jedis jedis) {
        return JSON.parseObject(jedis.get(key), cls.getClass());
    }


    /**
     * @param tableName
     * @param column
     * @param value
     * @param id
     * @Description:对指定值加排序
     * @return:void
     */
    public void zaddSort(String tableName, String column, String value, String id) {
        transaction.zadd(tableName + SPLIT_MARK + SORT + SPLIT_MARK + column, Double.parseDouble(value), id);
    }

    /**
     * 主键加索引
     * @param tableName
     * @param column
     * @param value
     */
    public void zaddIndex(String tableName , String column ,String value){
        transaction.sadd(tableName+SPLIT_MARK+INDEX+SPLIT_MARK+column,value);
    }

    /**
     * @Description:操作redis日志转换成对应的sql
     * @param sql
     * @return:Long
     */
    public void log(String sql){
        transaction.lpush(LOG,sql);
    }

    /**
     * @Description: 监听必须在开启事物之前，执行watch命令
     * @param keys
     * @return:void
     */
    public void watch(String... keys) {
        jedis.watch(keys);
    }

}

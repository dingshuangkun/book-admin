import redis.clients.jedis.Jedis;

/**
 * Created by dingshuangkun on 2018/2/5.
 */
public class Main {

    public static void main(String[] argrs){
        try {
            Jedis jedis = new Jedis("111.230.107.55",6379);
            jedis.auth("Dsk741852!");
            System.out.println( jedis.get("a"));
            jedis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

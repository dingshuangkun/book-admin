package util;

/**
 * Created by dingshuangkun on 2018/4/21.
 */
public class JedisConnetionFactory {

    private static String address ="111.230.107.55";
    private static int port = 6379;
    private static String auth = "Dsk741852!";

    private  static JedisConnection jedisConnection = new JedisConnection(address,port,auth);

    private JedisConnetionFactory(){

    }

    public static JedisConnection getJedisConnection(){
        return jedisConnection;
    }

}

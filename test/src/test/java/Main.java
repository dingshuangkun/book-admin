
import java.util.Scanner;

/**
 * Created by dingshuangkun on 2018/3/31.
 */
public class Main {

    public static void main(String[] args) {

    }

    public String getStr(String str){
        if(str==null){
            return "00000000";
        }else if(str.length()<8){
            int len = str.length();
            for(int i=len+1;i<=8;i++){
                str+="0";
            }
            return str;
        }
        return str.substring(0,8);
    }

}

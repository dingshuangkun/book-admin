
import java.util.Scanner;

/**
 * Created by dingshuangkun on 2018/3/31.
 */
public class Main {
    // (a* b)%n
    public static void main(String[] arges) {

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int n =sc.nextInt();
        int result = 1;
        if(y==0){
            System.out.println(1%n);
        }else if(y==1){
            System.out.println(x%n);
        }else if(y%2 == 0) {
            for(int i = 1 ;i<=y/2;i++) {
                 result *= ((x % n) * (x % n))%n;
            }
            System.out.println(result);
        }else {
            for(int i = 1 ;i<=y/2;i++){
                result *= ((x % n) * (x % n))%n;
            }
            System.out.println((x*result)%n);
        }
    }

}

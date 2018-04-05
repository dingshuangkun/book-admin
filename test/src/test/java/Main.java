import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by dingshuangkun on 2018/3/31.
 */
public class Main {

    public static void main(String[] arges){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int results[] = new int[n];
        for(int i = 0; i < n; i++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            int table = calc(r, g, b);
            results[i]=table;
        }
        for(int result:results){
            System.out.println(result);
        }
    }

    public static int calc(int a, int b, int c) {
        int table = 0;
        int[] arr = {a, b, c};
        Arrays.sort(arr);
        a = arr[0];
        b = arr[1];
        c = arr[2];
        if(c/2 == 0 || b == 0){
            return a;
        }else {
            int min=(c-a)/2 >= (b-a)? b-a:(c-a)/2;
            return a+min;
        }

    }

}

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by dingshuangkun on 2018/2/5.
 */
public class Main {


    static final String  URL = "https://b.faloo.com/";

    public static void main(String[] arges)throws Exception{
      Document doc = Jsoup.connect(URL).get();
       Elements links = doc.select("a[href]");
       for (Element link : links){
           System.out.println(link.data());
       }
    }
}

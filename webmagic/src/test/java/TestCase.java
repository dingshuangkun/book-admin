import com.spilder.entitys.Chapter;
import com.spilder.enums.NovelSiteEnum;
import com.spilder.impl.DefaultChapterDetailSpider;
import com.spilder.impl.DefaultChapterSpider;
import com.spilder.interfaces.IChapterDetailSpider;
import com.spilder.interfaces.IChapterSpider;
import com.spilder.util.NovelSpiderUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/3/25.
 */
public class TestCase {
    @Test
    public void test1() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters  = spider.getsChapter("http://www.biquge.tw/0_5/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }
    @Test
    public void testGetsChapter() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter>  chapters  = spider.getsChapter("http://www.biquge.tw/0_5/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }
    @Test
    public void testGetSiteContext() {
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.23wx.com/html/42/42377/")));
    }

    @Test
    public void testGetChapterDetail() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.23wx.com/html/42/42377/18781565.html").getContent());
    }
}

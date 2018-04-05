import com.spilder.configuration.Configuration;
import com.spilder.entitys.Chapter;
import com.spilder.entitys.Novel;
import com.spilder.enums.NovelSiteEnum;
import com.spilder.impl.chapter.BxwxChapterSpider;
import com.spilder.impl.chapter.DefaultChapterDetailSpider;
import com.spilder.impl.chapter.DefaultChapterSpider;
import com.spilder.impl.download.NovelDownload;
import com.spilder.interfaces.IChapterDetailSpider;
import com.spilder.interfaces.IChapterSpider;
import com.spilder.interfaces.INovelDownlowd;
import com.spilder.interfaces.INovelSpider;
import com.spilder.util.NovelSpiderFactory;
import com.spilder.util.NovelSpiderUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingshuangkun on 2018/3/25.
 */
public class TestCase {
    @Test
    public void testGetsChapter() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter>  chapters  = spider.getsChapter("http://www.biquge.tw/0_5/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }
    /**
     * 该测试方法用来获取看书中网站的章节列表
     * @throws Exception
     */
    @Test
    public void testGetsChapter2() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter>  chapters  = spider.getsChapter("http://www.kanshuzhong.com/book/1236/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }
    /**
     * 测试是否能够正确的拿到笔下文学的章节列表
     * @throws Exception
     */
    @Test
    public void testGetsChapter3() throws Exception {
        IChapterSpider spider = new BxwxChapterSpider();
        List<Chapter>  chapters  = spider.getsChapter("http://www.bxwx8.org/b/70/70093/");
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

    /**
     * 该测试方法用于测试是否能拿到看书中网站的章节详细内容
     */
    @Test
    public void testGetChapterDetail2() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.kanshuzhong.com/book/1236/12899575.html"));
    }
    /**
     * 该测试方法用于测试手否能正确拿到笔下文学的章节消息内容
     */
    @Test
    public void testGetChapterDetail3() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.bxwx8.org/b/70/29204416.html"));
    }

    @Test
    public void testDownload() {
        INovelDownlowd download = new NovelDownload();
        Configuration config = new Configuration();
        config.setPath("D:/1");
        config.setSize(50);
        config.setTryTimes(10);
        System.out.println("下载好了，文件保存在：" + download.download("http://www.23wx.com/html/42/42377/", config) + "这里，赶紧去看看吧！");
    }
    @Test
    public void testSubList() {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 10 ;i++) {
            ints.add(i);
        }
        System.out.println(ints);
        //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        //0-4
        System.out.println(ints.subList(0, 5));
    }

    @Test
    public void testMultiFileMerge() {
        NovelSpiderUtil.multiFileMerge("D:/1", null, true);
    }

    @Test
    public void testKanShuZhongGetsNovel() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.kanshuzhong.com/map/A/1/");
        List<Novel> novels = spider.getsNovel("http://www.kanshuzhong.com/map/A/1/");
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }
    @Test
    public void testBxwxGetsNovel() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.bxwx8.org/binitialE/0/1.htm");
        List<Novel> novels = spider.getsNovel("http://www.bxwx8.org/binitialE/0/1.htm");
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }
}

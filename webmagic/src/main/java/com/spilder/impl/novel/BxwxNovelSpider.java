package com.spilder.impl.novel;

import java.util.ArrayList;
import java.util.List;

import com.spilder.entitys.Novel;
import com.spilder.enums.NovelSiteEnum;
import com.spilder.util.NovelSpiderUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 笔下文学网站的书籍列表爬取
 * @author dingshuangkun
 * @date   2018/04/05
 */
public class BxwxNovelSpider extends AbstractNovelSpider {

	public BxwxNovelSpider() {
	}

	@Override
	public List<Novel> getsNovel(String url) {
		List<Novel> novels = new ArrayList<>();
		try {
			Elements trs = super.getsTr(url, 2);
			for (int index = 1, size = trs.size(); index < size; index++) {
				Element tr = trs.get(index);
				Elements tds = tr.getElementsByTag("td");
				Novel novel = new Novel();
				novel.setName(tds.first().text());
				String novelUrl = tds.first().getElementsByTag("a").first().absUrl("href").replace(".htm", "/").replace("/binfo/", "/b/");
				novel.setUrl(novelUrl);
				novel.setLastUpdateChapter(tds.get(1).text());
				novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
				novel.setAuthor(tds.get(2).text());
				novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).text(), "yy-MM-dd"));
				novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));
				novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
				novels.add(novel);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return novels;
	}

}

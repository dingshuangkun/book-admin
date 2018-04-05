package com.spilder.impl.chapter;

import java.util.ArrayList;
import java.util.List;

import com.spilder.entitys.Chapter;
import com.spilder.enums.NovelSiteEnum;
import com.spilder.interfaces.IChapterSpider;
import com.spilder.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author dingshuangkun
 * on 2018/04/05
 * 抓取章节对应的标题和url
 */
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {
	@Override
	public List<Chapter> getsChapter(String url) {
		try {
			String result = crawl(url);
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			List<Chapter> chapters = new ArrayList<>();
			for (Element a : as) {
				Chapter chapter = new Chapter();
				chapter.setTitle(a.text());
				chapter.setUrl(a.absUrl("href"));
				chapters.add(chapter);
			}
			return chapters;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

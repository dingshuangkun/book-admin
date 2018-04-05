package com.spilder.impl.chapter;
import com.spilder.entitys.Chapter;
import java.util.Collections;
import java.util.List;

/**
 * @author dingshuangkun
 * on 2018/04/05
 * 获取笔下文学的章节列表
 */
public class BxwxChapterSpider extends AbstractChapterSpider {
	@Override
	public List<Chapter> getsChapter(String url) {
		List<Chapter> chapters = super.getsChapter(url);
		Collections.sort(chapters,(o1,o2)->{
			String o1Url = o1.getUrl();
			String o2Url = o2.getUrl();
			String o1Index = o1Url.substring(o1Url.lastIndexOf('/') + 1, o1Url.lastIndexOf('.'));
			String o2Index = o2Url.substring(o2Url.lastIndexOf('/') + 1, o2Url.lastIndexOf('.'));
			return o1Index.compareTo(o2Index);
		});
		return chapters;
	}
}

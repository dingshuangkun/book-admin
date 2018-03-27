package com.spilder.interfaces;

import com.spilder.entitys.Chapter;

import java.util.List;

public interface IChapterSpider {
	/**
	 * 根据URL返回小说章节
	 *
	 * @param url
	 * @return
	 */
	List<Chapter> getsChapter(String url);
}



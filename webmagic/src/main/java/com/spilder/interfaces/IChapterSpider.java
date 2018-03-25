package com.spilder.interfaces;

import com.spilder.entitys.Chapter;

import java.util.List;

public interface IChapterSpider {
	/**
	 *
	 * @param url
	 * @return
	 */
	public List<Chapter> getsChapter(String url);
}

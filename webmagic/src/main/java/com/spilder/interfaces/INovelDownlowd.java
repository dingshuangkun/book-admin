package com.spilder.interfaces;

import com.spilder.configuration.Configuration;

/**
 * @author dingshuangkun
 * on 2018/04/05
 */
public interface INovelDownlowd {
	/**
	 * 根据URL 下载章节内容
	 * @param url
	 * @return
	 */
	public String download(String url , Configuration configuration);
}

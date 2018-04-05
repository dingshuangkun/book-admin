package com.spilder.entitys;

import lombok.Data;

/**
 *小说章节定义
 */
@Data
public class Chapter {
	/**
	 * 小说标题
	 */
	private String title;
	/**
	 * 对应的url
	 */
	private String url;
}

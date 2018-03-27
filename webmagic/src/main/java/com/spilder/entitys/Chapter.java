package com.spilder.entitys;

import lombok.Data;

import java.io.Serializable;

/**
 *小说章节定义
 */
@Data
public class Chapter implements Serializable {

	private String title;
	private String url;
}

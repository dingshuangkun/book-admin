package com.spilder.storage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.mysql.dao.NovelDAO;
import com.spilder.entitys.Novel;
import com.spilder.interfaces.INovelSpider;
import com.spilder.util.NovelSpiderFactory;
import com.spilder.util.convert.NovelConvert;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractNovelStorage implements Processor {
    @Autowired
	private NovelDAO novelDAO;
	protected Map<String, String> tasks = new TreeMap<>();


	@Override
	public void process() {
		ExecutorService service = Executors.newFixedThreadPool(tasks.size());
		List<Future<String>> futures = new ArrayList<>(tasks.size());
		for (Map.Entry<String, String> entry : tasks.entrySet()) {
			final String key = entry.getKey();
			final String value = entry.getValue();
			futures.add(service.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					INovelSpider spider = NovelSpiderFactory.getNovelSpider(value);
					Iterator<List<Novel>> iterator = spider.iterator(value, 10);
					while (iterator.hasNext()) {
						System.err.println("开始抓取[" + key + "] 的 URL:" + spider.next());
						List<Novel> novels = iterator.next();
						for (Novel novel : novels) {
							//设置小说的名字的首字母
							novel.setFirstLetter(key.charAt(0));
						}
						NovelConvert novelConvert = new NovelConvert();
						novelDAO.batchInsert(novelConvert.to(novels));
						Thread.sleep(1000);
					}
					return key;
				}

			}));
		}
		service.shutdown();
		for (Future<String> future : futures) {
			try {
				System.out.println("抓取[" + future.get() + "]结束！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

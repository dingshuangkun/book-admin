package com.spilder.impl;

import com.mysql.model.BookChapterDO;
import com.spilder.configuration.Configuration;
import com.spilder.entitys.Chapter;
import com.spilder.entitys.ChapterDetail;
import com.spilder.interfaces.IChapterDetailSpider;
import com.spilder.interfaces.IChapterSpider;
import com.spilder.interfaces.INovelDownlowd;
import com.spilder.util.ChapterDetailSpiderFactory;
import com.spilder.util.ChapterSpiderFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dingshuangkun
 * on 2018/04/05
 * 如何实现多线程下载任意网站的小说
 * 1.拿到该网站的某本小说的所有章节：章节列表
 * 2.通过计算，将这些章节分配给指定数量的线程，让他们去解析，然后保存到文本文件中
 * 3.通知主线程，将这些零散的小文件合并成一个大文件。最后将那些分片的小文件删除掉。
 */
public class NovelDownload implements INovelDownlowd {

    private CountDownLatch countDownLatch;

    @Override
    public String download(String url, Configuration configuration) {
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapters = chapterSpider.getsChapter(url);
        BookChapterDO bookChapterDO = new BookChapterDO();
        int size = configuration.getSize();
        //某个线程下载完毕之后，你得告诉主线程：我下载好了
        int maxThreadSize = (int) Math.ceil(chapters.size() * 1.0 / size);
        Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<>();
        for (int i = 0; i < maxThreadSize; i++) {
            int fromIndex = i * (configuration.getSize());
            if (i == maxThreadSize - 1) {
                int toIndex = chapters.size() - 1;
            }
            int toIndex = i == maxThreadSize - 1 ? chapters.size() - 1 : i * (configuration.getSize()) + configuration.getSize() - 1;
            downloadTaskAlloc.put(fromIndex + "-" + toIndex, chapters.subList(fromIndex, toIndex));
        }
        ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
        Set<String> keySet = downloadTaskAlloc.keySet();
        countDownLatch = new CountDownLatch(keySet.size());
        for (String key : keySet) {
            service.submit(new DownloadCallable(downloadTaskAlloc.get(key)));
        }
        service.shutdown();
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    class DownloadCallable implements Runnable {
        private List<Chapter> chapters;

        public DownloadCallable(List<Chapter> chapters) {
            this.chapters = chapters;
        }

        @Override
        public void run() {
            for (Chapter chapter : chapters) {
                IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(chapter.getUrl());
                ChapterDetail detail = spider.getChapterDetail(chapter.getUrl());
                System.out.println(detail.getTitle());
                System.out.println(detail.getContent());
                countDownLatch.countDown();
                // detail.getContent();
                // 写入数据库
            }
        }

    }
}

package com.spilder.impl.download;
import com.spilder.configuration.Configuration;
import com.spilder.entitys.Chapter;
import com.spilder.entitys.ChapterDetail;
import com.spilder.enums.NovelSiteEnum;
import com.spilder.interfaces.IChapterDetailSpider;
import com.spilder.interfaces.IChapterSpider;
import com.spilder.interfaces.INovelDownlowd;
import com.spilder.util.ChapterDetailSpiderFactory;
import com.spilder.util.ChapterSpiderFactory;
import com.spilder.util.NovelSpiderUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author dingshuangkun
 * on 2018/04/05
 * 如何实现多线程下载任意网站的小说
 * 1.拿到该网站的某本小说的所有章节：章节列表
 * 2.通过计算，将这些章节分配给指定数量的线程，让他们去解析，然后保存到文本文件中
 * 3.通知主线程，将这些零散的小文件合并成一个大文件。最后将那些分片的小文件删除掉。
 */
public class NovelDownload implements INovelDownlowd {

    @Override
    public String download(String url, Configuration config) {
        IChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapters = spider.getsChapter(url);
        //某个线程下载完毕之后，你得告诉主线程：我下载好了
        //所有的线程都下载好了，合并！！！
        int size = config.getSize();
        // 2010章 100章
        // 需要21个线程
        // 一个int / int 结果只能是int
        // 一个double / double 结果依然是double
        //一个double / int 结果是double
        //Math.ceil(double) 10 -> 10 10.5->11 10.1 ->11 -10 -> -10 -10.1 -> 10 -10.5 -> -10
        int maxThreadSize = (int)Math.ceil(chapters.size() * 1.0 / size);
        Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<>();
        for (int i = 0; i < maxThreadSize; i++) {
            // i = 0 0-99	-- > 100  0 100
            // i = 1 100-199
            // i = 2 200-299
            // i = 3 300-399
            // ...
            // i = 19 1900-1999
            // i = 20 2000-2052
            // 总共才2053章
            int fromIndex = i * config.getSize();
            int toIndex = i == maxThreadSize - 1 ? chapters.size() : i * config.getSize() + config.getSize();
            downloadTaskAlloc.put(fromIndex + "-" + toIndex, chapters.subList(fromIndex, toIndex));
        }
        ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
        Set<String> keySet = downloadTaskAlloc.keySet();
        List<Future<String>> tasks = new ArrayList<>();

        //通过这两段代码就可以创建缺失的路径
        String savePath = config.getPath() + "/" + NovelSiteEnum.getEnumByUrl(url).getUrl();
        new File(savePath).mkdirs();

        for (String key : keySet) {

            tasks.add(service.submit(new DownloadCallable(savePath + "/" + key + ".txt", downloadTaskAlloc.get(key), config.getTryTimes())));
        }
        service.shutdown();
        for (Future<String> future : tasks) {
            try {
                System.out.println(future.get() + ",下载完成！");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        NovelSpiderUtil.multiFileMerge(savePath, null, true);
        return savePath + "/merge.txt";
    }
}

class DownloadCallable implements Callable<String> {
    private List<Chapter> chapters;
    private String path;
    private int tryTimes;
    public DownloadCallable(String path, List<Chapter> chapters, int tryTimes) {
        this.path = path;
        this.chapters = chapters;
        this.tryTimes = tryTimes;
    }
    @Override
    public String call() throws Exception {
        try (
                PrintWriter out = new PrintWriter(new File(path), "UTF-8");
        ) {
            for (Chapter chapter : chapters) {
                IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(chapter.getUrl());
                ChapterDetail detail = null;

                for (int i = 0; i < tryTimes; i++) {
                    try {
                        detail = spider.getChapterDetail(chapter.getUrl());
                        out.println(detail.getTitle());
                        out.println(detail.getContent());
                        break;
                    } catch (RuntimeException e) {
                        System.err.println("尝试第[" + (i + 1) + "/" + tryTimes + "]次下载失败了！");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
}

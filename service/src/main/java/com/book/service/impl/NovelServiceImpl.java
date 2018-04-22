package com.book.service.impl;

import com.book.enums.BookState;
import com.book.service.ChapterService;
import com.book.service.NovelService;
import com.book.util.TimeUtil;
import com.book.vo.ChapterVO;
import com.book.vo.NovelVO;
import com.mysql.dao.ChapterDAO;
import com.mysql.dao.ChapterDetailDAO;
import com.mysql.dao.NovelDAO;
import com.mysql.model.ChapterDO;
import com.mysql.model.ChapterDetailDO;
import com.mysql.model.NovelDO;
import com.mysql.query.QueryChapter;
import com.mysql.query.QueryNovel;
import com.spilder.entitys.Chapter;
import com.spilder.entitys.ChapterDetail;
import com.spilder.entitys.Novel;
import com.spilder.impl.chapter.DefaultChapterDetailSpider;
import com.spilder.impl.chapter.DefaultChapterSpider;
import com.spilder.interfaces.IChapterDetailSpider;
import com.spilder.interfaces.IChapterSpider;
import com.spilder.interfaces.INovelSpider;
import com.spilder.util.NovelSpiderFactory;
import com.spilder.util.convert.NovelConvert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dingshuangkun on 2018/4/12.
 */
@Service
public class NovelServiceImpl implements NovelService {

    private static String IS_QUERY_CHAPTER = "true";

    @Autowired
    private NovelDAO novelDAO;
    @Autowired
    private ChapterDAO chapterDAO;
    @Autowired
    private ChapterDetailDAO chapterDetailDAO;
    @Autowired
    private ChapterService chapterService;

    @Override
    public Integer insertNovel() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.kanshuzhong.com/map/A/1/");
        Iterator<List<Novel>> iterator = spider.iterator("http://www.kanshuzhong.com/map/A/1/", 10);
        int i = 1;
        while (iterator.hasNext()) {
            List<Novel> novels = iterator.next();

            for (Novel novel : novels) {
                NovelConvert novelConvert = new NovelConvert();
                NovelDO novelDO = novelConvert.to(novel);

                //  novelDAO.insert(novelDO);
                System.out.println("插入第" + i + "篇小说");
                i++;
            }
        }
        return null;
    }

    @Override
    public Integer
    insertChapter() {
        try {
            for (int i = 969; i <= 1048; i++) {
                NovelDO novelDO = novelDAO.selectByPrimaryKey(Long.valueOf(i));
                if (novelDO != null) {
                    Thread.sleep(1000);
                    String url = novelDO.getUrl();
                    Long id = novelDO.getId();
                    IChapterSpider spider = new DefaultChapterSpider();
                    List<Chapter> chapters = spider.getsChapter(url);
                    int j = 1;
                    for (Chapter chapter : chapters) {
                        if (j == 25) {
                            break;
                        }
                        ChapterDO chapterDO = new ChapterDO();
                        chapterDO.setBookId(id);
                        chapterDO.setTitle(chapter.getTitle());
                        chapterDO.setUrl(chapter.getUrl());
                        chapterDAO.insertChapter(chapterDO);
                        System.out.println(chapter.getTitle());
                        j++;
                    }
                    System.out.println("book_id=" + i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer insertContent() {
        try {
            for (int bookId = 969; bookId <= 1048; bookId++) {
                QueryChapter queryChapter = new QueryChapter();
                queryChapter.setBookId(Long.valueOf(bookId));
                List<ChapterDO> chapterDOList = chapterDAO.selectChapter(queryChapter);
                if (chapterDOList != null && chapterDOList.size() > 0) {
                    ChapterDO chapterDO = chapterDOList.get(0);
                    IChapterDetailSpider spider = new DefaultChapterDetailSpider();
                    ChapterDetail chapterDetail = spider.getChapterDetail(chapterDO.getUrl());
                    ChapterDetailDO chapterDetailDO = new ChapterDetailDO();
                    chapterDetailDO.setId(chapterDO.getId());
                    chapterDetailDO.setTitle(chapterDO.getTitle());
                    chapterDetailDO.setTitle(chapterDetailDO.getTitle());
                    chapterDetailDO.setPrevUrl(chapterDetail.getPrev());
                    chapterDetailDO.setNextUrl(chapterDetail.getNext());
                    chapterDetailDO.setContent(chapterDetail.getContent());
                    if (chapterDetailDO.getPrevUrl() == null && chapterDetailDO.getNextUrl() == null) {
                        chapterDetailDO.setPrevId(0L);
                        chapterDetailDO.setNextId(0L);
                    } else if (chapterDetailDO.getPrevUrl() == null) {
                        chapterDetailDO.setPrevId(0L);
                        chapterDetailDO.setNextId((chapterDetailDO.getId() + 1));
                    } else if (chapterDetailDO.getNextUrl() == null) {
                        chapterDetailDO.setNextId(0L);
                        chapterDetailDO.setPrevId((chapterDetailDO.getId() - 1));
                    } else {
                        Long id = chapterDetailDO.getId();
                        chapterDetailDO.setPrevId(id - 1);
                        if (id >= 24) {
                            chapterDetailDO.setNextId(0L);
                        } else {
                            chapterDetailDO.setNextId(id + 1);
                        }
                    }
                    chapterDetailDAO.insertChapterDetail(chapterDetailDO);
                    System.out.println("bookId=" + bookId);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NovelVO> queryNovel(QueryNovel queryNovel) {
        List<NovelDO> noveDOList = novelDAO.selectByQueryNovel(queryNovel);
        List<NovelVO> novelVOList = new ArrayList<>();
        SimpleDateFormat time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        if (noveDOList != null && noveDOList.size() > 0) {
            noveDOList.forEach(n -> {
                QueryChapter queryChapter = new QueryChapter();
                queryChapter.setBookId(n.getId());
                NovelVO novelVO = new NovelVO();
                novelVO.setId(n.getId());
                novelVO.setAddTime(time.format(n.getAddTime()));
                novelVO.setAuthor(n.getAuthor());
                novelVO.setBookName(n.getBookName());
                if (BookState.getByType(n.getBookState()) != null) {
                    novelVO.setBookState(BookState.getByType(n.getBookState()).getDesc());
                }
                novelVO.setBookType(n.getBookType());
                novelVO.setLastUpdateChapter(n.getLastUpdateChapter());
                novelVO.setLastUpdateChapterUrl(n.getLastUpdateChapterUrl());
                novelVO.setUpdateTime(time.format(n.getUpdateTime()));
                novelVO.setUrl(n.getUrl());
                if (IS_QUERY_CHAPTER.equals(queryNovel.getQueryChapters())) {
                    List<ChapterVO> chapterVOList = chapterService.queryChapterByBookId(n.getId());
                    novelVO.setChapters(chapterVOList);
                }
                novelVOList.add(novelVO);
            });
        }

        return novelVOList;
    }


    @Override
    public List<NovelVO> queryLikeByauthorOrTitle(String authorOrTitle) {
        authorOrTitle = authorOrTitle + "%";
        List<NovelDO> novelDOS = novelDAO.selectLikeByAuthorOrTitle(authorOrTitle);
        List<NovelVO> novelVOS = new ArrayList<>();
        novelDOS.forEach(n -> {
            NovelVO novelVO = new NovelVO();
            BeanUtils.copyProperties(n, novelVO);
            novelVO.setUpdateTime(TimeUtil.formatYYYY_MM_dd(n.getUpdateTime()));
            novelVOS.add(novelVO);
        });
        return novelVOS;
    }
}

package com.book.cache;

import com.book.vo.NovelVO;

/**
 * Created by dingshuangkun on 2018/4/24.
 */
public interface CacheNovelService {

    NovelVO queryById(Long id);
}

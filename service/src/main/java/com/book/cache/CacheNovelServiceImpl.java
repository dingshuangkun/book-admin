package com.book.cache;

import com.book.service.NovelService;
import com.book.vo.NovelVO;
import com.mysql.model.NovelDO;
import com.redis.service.RedisNovelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dingshuangkun on 2018/4/24.
 */
@Service
public class CacheNovelServiceImpl implements CacheNovelService {

    @Autowired
    private RedisNovelService redisNovelService;

    private NovelService novelService;
    @Override
    public NovelVO queryById(Long id) {
        NovelDO novelDO =  redisNovelService.queryById(id);
        if(novelDO == null){
          NovelVO novelVO =  novelService.queryNovelById(id);
          if(novelVO!=null){
              BeanUtils.copyProperties(novelVO,novelDO);
              redisNovelService.addNovelDO(novelDO);
              return novelVO;
          }
          return null;
        }else {
           NovelVO novelVO = new NovelVO();
           BeanUtils.copyProperties(novelDO,novelVO);
           return novelVO;
        }
    }
}

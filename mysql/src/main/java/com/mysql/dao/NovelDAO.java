package com.mysql.dao;

import com.mysql.model.NovelDO;
import com.mysql.query.QueryNovel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/4/7.
 */
@Repository
public interface NovelDAO {
    /**
     * 根据id 删除小说
     * @param id
     * @return
     */
     int deleteByPrimaryKey(Long id);

    /**
     * 插入小说
     * @param record
     * @return
     */
     int insert(NovelDO record);

     int insertSelective(NovelDO record);

    /**
     * 查询小说
     * @param id
     * @return
     */
     NovelDO selectByPrimaryKey(Long id);

    /**
     * 根据queryNovel分页查询
     * @param queryNovel
     * @return
     */
     List<NovelDO> selectByQueryNovel(QueryNovel queryNovel);

    /**
     * 根据作者或者书名进行查询
     * @param authorOrTitle
     * @return
     */
     List<NovelDO> selectLikeByAuthorOrTitle(String authorOrTitle);

     int updateByPrimaryKeySelective(NovelDO record);

    /**
     * 修改
     * @param record
     * @return
     */
     int updateByPrimaryKey(NovelDO record);

    /**
     * 批量插入
     * @param novels
     */
     void batchInsert(List<NovelDO> novels);
}

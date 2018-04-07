package com.mysql.dao;

import com.mysql.model.NovelDO;
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

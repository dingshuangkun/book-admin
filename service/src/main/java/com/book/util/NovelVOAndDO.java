package com.book.util;

import com.book.enums.BookState;
import com.book.vo.NovelVO;
import com.mysql.model.NovelDO;
import org.springframework.beans.BeanUtils;

/**
 * Created by dingshuangkun on 2018/4/26.
 */
public class NovelVOAndDO implements VOAndDO<NovelVO,NovelDO> {

    @Override
    public NovelVO from(NovelDO n) {
        if(n!=null) {
            NovelVO novelVO = new NovelVO();
            BeanUtils.copyProperties(n, novelVO);
            novelVO.setUpdateTime(TimeUtil.formatYYYY_MM_dd(n.getUpdateTime()));
            novelVO.setAddTime(TimeUtil.formatYYYY_MM_dd(n.getAddTime()));
            novelVO.setBookState(BookState.getByType(n.getBookState()).getDesc());
            return novelVO;
        }
        return null;
    }

    @Override
    public NovelDO to(NovelVO v) {
        return null;
    }
}

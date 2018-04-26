package com.book.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  dingshuangkun
 * @date on 2018/4/26.
 */
public interface  VOAndDO<VO,DO> {

    VO from (DO d);

    DO to(VO v);

   default List<VO> from(List<DO> dos){
       List<VO> list = new ArrayList<>();
       if(CollectionUtil.isNotEmpty(dos)) {
           dos.forEach(n -> {
               list.add(from(n));
           });
           return list;
       }
       return null;
   }

   default List<DO> to(List<VO> vos){
       List<DO> list = new ArrayList<>();
       if(CollectionUtil.isNotEmpty(vos)){
           vos.forEach(n->{
               list.add(to(n));
           });
           return list;
       }
       return null;
   }
}

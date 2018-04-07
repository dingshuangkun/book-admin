package com.spilder.util.convert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  dingshuangkun
 * @date  on 2018/4/7.
 */
public interface  ConvertUtil<A,B> {
    abstract  A from(B b);

    abstract  B to (A b);

    default List<A> from(List<B> bs){
        if(bs!=null&&bs.size()>0){
            List<A> as = new ArrayList<>();
            for(B b : bs){
                as.add(from(b));
            }
            return as;
        }
        return null;
    }

   default List<B> to (List<A> as) {
        if (as != null && as.size() > 0) {
            List<B> bs = new ArrayList<>();
            for (A a : as) {
                bs.add(to(a));
            }
            return bs;
        }
        return null;
    }
}

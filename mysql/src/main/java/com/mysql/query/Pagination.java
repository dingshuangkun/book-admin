package com.mysql.query;
/**
 * Created by dingshuangkun on 2018/4/15.
 */

public class Pagination {
    private Integer beginIndex;
    private Integer endIndex;

    public Integer getBeginIndex() {
        if(beginIndex == null){
            return null;
        }
        return beginIndex == 0 ? 0:(beginIndex-1)*endIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }
}

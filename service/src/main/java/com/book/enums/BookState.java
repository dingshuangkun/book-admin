package com.book.enums;

import lombok.Getter;

/**
 * Created by dingshuangkun on 2018/4/15.
 */
public enum BookState {
    VALID(1,"有效"),
    INVALID(0,"无效");
    @Getter
    private int type;
    @Getter
    private String desc;
    BookState(int type , String desc){
        this.type = type;
        this.desc = desc;
    }

    public static BookState getByType(int type){
        for(BookState bookType : values()){
            if(bookType.type == type){
                return bookType;
            }
        }
        return null;
    }
}

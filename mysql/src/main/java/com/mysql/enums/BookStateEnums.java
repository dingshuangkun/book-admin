package com.mysql.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by dingshuangkun on 2018/5/25.
 */
public enum BookStateEnums {

    VISIBLE(1, "用户可见"),
    DISVISIBLE(0, "用户不可见");

    @Getter@Setter
    private Integer code;
    @Getter@Setter
    private String desc;

    BookStateEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public BookStateEnums getByCode(Integer code){
        for (BookStateEnums bookStateEnums : values()){
            if(bookStateEnums.getCode().equals(code)){
                return bookStateEnums;
            }
        }
        return null;
    }

}

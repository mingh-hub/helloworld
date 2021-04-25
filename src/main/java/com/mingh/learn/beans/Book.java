package com.mingh.learn.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseBean{
    private static final long serialVersionUID = -5966361323385755069L;

    /**
     * 书籍编号
     */
    private String bookName;
    /**
     * 作者
     */
    private String author;
    /**
     * 书籍编号
     */
    private String isbn;
    /**
     * 书籍单价
     */
    private Double price;
    /**
     * 书籍种类
     */
    private String category;
}

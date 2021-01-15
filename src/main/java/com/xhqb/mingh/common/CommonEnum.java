package com.xhqb.mingh.common;

import com.xhqb.mingh.beans.Book;

/**
 * 常用枚举类
 */
public class CommonEnum {

    public enum BookType {
        /**
         * Effective Java
         */
        EFFECTIVE_JAVA("<<Effective Java>>", "Joshua Bloch", "9787111255833", 52.00, "编程技术"),
        /**
         * 深入理解Java虚拟机（第2版
         */
        VIRTUAL_MACHINE_JAVA("<<深入理解Java虚拟机（第2版)>>", "周志明", "9787111421900", 79.00, "编程技术"),
        /**
         * Linux
         */
        LINUX("<<Linux>>", "Michael Kerrisk", "9787115328670", 49.00, "编程技术"),
        /**
         * 史记
         */
        RECORDS_OF_THE_HISTORIAN("<<史记>>", "司马迁", "9787101003048", 125.00, "中国文学"),
        /**
         * 红楼梦
         */
        DREAM_OF_THE_RED_CHAMBER("<<红楼梦>>", "", "9787020002207", 59.70, "中国文学"),
        /**
         * 围城
         */
        FORTRESS_BESIEGED("<<围城>>", "钱钟书", "9787020024759", 19.00, "中国文学"),
        /**
         * 烧纸
         */
        BURNING_PAPER("<<烧纸>>", "李沧东", "9787307207004", 58.00, "外国文学"),
        /**
         * 安娜·卡列尼娜
         */
        ANNA_KARENINA("<<安娜·卡列尼娜>>", "列夫·托尔斯泰", "9787532132256", 37.00, "外国文学"),
        /**
         * 飘
         */
        GONE_WITH_THE_WIND("<<飘>>", "玛格丽特·米切尔", "9787806570920", 40.00, "外国文学"),
        ;

        private String name;
        private String author;
        private String isbn;
        private Double price;
        private String category;

        /**
         * BookType --> Book
         *
         * @param bookType
         * @return
         */
        public static Book build(BookType bookType) {
            return Book.builder().bookName(bookType.name).author(bookType.author).isbn(bookType.isbn).price(bookType.price).category(bookType.category).build();
        }

        BookType(String name, String author, String isbn, Double price, String category) {
            this.name = name;
            this.author = author;
            this.isbn = isbn;
            this.price = price;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public String getIsbn() {
            return isbn;
        }

        public Double getPrice() {
            return price;
        }

        public String getCategory() {
            return category;
        }
    }
}

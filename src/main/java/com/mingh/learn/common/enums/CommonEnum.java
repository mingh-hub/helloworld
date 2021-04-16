package com.mingh.learn.common.enums;

import com.mingh.learn.beans.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @MethodName
 * @Author Hai.Ming
 * @Date 2021/2/1 8:35 下午
 * @Description 常用枚举类
 **/
public class CommonEnum {

    /**
     * @EnumName
     * @Author Hai.Ming
     * @Date 2021/2/12 10:44 下午
     * @Description 正则表达式
     **/
    public enum Regex{
        /**
         * 任意空格表达式
         **/
        WHITESPACE("\\s+", "任意空格表达式"),
        /**
         * 水平空格表达式
         **/
        HORIZONTAL_WHITESPACE("\\h+", "水平空格表达式"),
        /**
         * 垂直空格表达式 \n
         **/
        VERTICAL_WHITESPACE("\\v+", "垂直空格表达式"),
        ;

        /**
         * 正则表达式
         **/
        private String regex;
        /**
         * 匹配规则释义
         **/
        private String explain;

        Regex(String regex, String explain) {
            this.regex = regex;
            this.explain = explain;
        }

        public String getRegex() {
            return regex;
        }

        public String getExplain() {
            return explain;
        }
    }

    /**
     * @EnumName
     * @Author Hai.Ming
     * @Date 2021/2/1 8:21 下午
     * @Description 文件类型
     **/
    public enum FileType{
        /**
         * JPEG (jpg)
         **/
        FILE_TYPE_JPG("jpg","FFD8FF"),
        /**
         * PNG (png)
         **/
        FILE_TYPE_PNG("png","89504E47"),
        /**
         * GIF (gif)
         **/
        FILE_TYPE_GIF("gif","47494638"),
        /**
         * TIFF (tif)
         **/
        FILE_TYPE_TIF("tif","49492A00"),
        /**
         * Windows Bitmap (bmp)
         **/
        FILE_TYPE_BMP("bmp","424D"),
        /**
         * CAD (dwg)
         **/
        FILE_TYPE_DWG("dwg","41433130"),
        /**
         * HTML (html)
         **/
        FILE_TYPE_HTML("html","68746D6C3E"),
        /**
         * Rich Text Format (rtf)
         **/
        FILE_TYPE_RTF("rtf","7B5C727466"),
        /**
         * XML
         **/
        FILE_TYPE_XML("xml","3C3F786D6C"),
        /**
         * ZIP
         **/
        FILE_TYPE_ZIP("zip","504B0304"),
        /**
         * RAR
         **/
        FILE_TYPE_RAR("rar","52617221"),
        /**
         * PSD
         **/
        FILE_TYPE_PSD("psd","52617221"),
        /**
         * EML
         **/
        FILE_TYPE_EML("eml","44656C69766572792D646174653A"),
        /**
         * DBX
         **/
        FILE_TYPE_DBX("dbx","CFAD12FEC5FD746F"),
        /**
         * Outlook (pst)
         **/
        FILE_TYPE_PST("pst","2142444E"),
        /**
         * MS Word
         **/
        FILE_TYPE_XLS("xls","D0CF11E0"),
        /**
         * MS Excel 注意：word 和 excel的文件头一样
         **/
        FILE_TYPE_DOC("doc","D0CF11E0"),
        /**
         * MS Access (mdb)
         **/
        FILE_TYPE_MDB("mdb","5374616E64617264204A"),
        /**
         * WordPerfect (wpd
         **/
        FILE_TYPE_WPD("wpd","FF575043"),
        /**
         * EPS
         **/
        FILE_TYPE_EPS("eps","252150532D41646F6265"),
        /**
         * PS
         **/
        FILE_TYPE_PS("ps","252150532D41646F6265"),
        /**
         * Adobe Acrobat (pdf)
         **/
        FILE_TYPE_PDF("pdf","255044462D312E"),
        /**
         * Quicken (qdf)
         **/
        FILE_TYPE_QDF("qdf","AC9EBD8F"),
        /**
         * Windows Password (pwl)
         **/
        FILE_TYPE_PWL("pwl","E3828596"),
        /**
         * Wave (wav)
         **/
        FILE_TYPE_WAV("wav","57415645"),
        /**
         * AVI
         **/
        FILE_TYPE_AVI("avi","41564920"),
        /**
         * Real Audio (ram)
         **/
        FILE_TYPE_RAM("ram","2E7261FD"),
        /**
         * Real Media (rm)
         **/
        FILE_TYPE_RM("rm","2E524D46"),
        /**
         * MPG
         **/
        FILE_TYPE_MPG("mpg","000001BA"),
        /**
         * Quicktime (mov)
         **/
        FILE_TYPE_MOV("mov","6D6F6F76"),
        /**
         * Windows Media (asf)
         **/
        FILE_TYPE_ASF("asf","3026B2758E66CF11"),
        /**
         * MIDI (mid)
         **/
        FILE_TYPE_MID("mid","4D546864"),
        ;
        /**
         * 文件类型
         **/
        private String type;
        /**
         * 数据前缀
         **/
        private String prefix;

        /**
         * @MethodName getFileTypeByPrefix
         * @Author Hai.Ming
         * @Date 2021/2/1 8:57 下午
         * @Description get file type by file data
         **/
        public static String getFileTypeByData(String data) {
            return Arrays.stream(values())
                    .filter(type -> StringUtils.startsWith(data, type.getPrefix()))
                    .map(FileType::getType)
                    .findAny()
                    .orElse(StringUtils.EMPTY);
        }

        FileType(String type, String prefix) {
            this.type = type;
            this.prefix = prefix;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }
    }

    /**
     * @EnumName BookType
     * @Author Hai.Ming
     * @Date 2021/1/29 8:53 下午
     * @Description 书籍枚举
     **/
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

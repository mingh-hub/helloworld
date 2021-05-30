package com.mingh.learn.utils;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName TimeUtils
 * @Author Hai.Ming
 * @Date 2021/1/27 8:30 下午
 * @Desc 日期时间工具类
 */
public class TimeUtils {

    public static final String DATE_FORMATTER_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMATTER_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMATTER_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATTER_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    private static Clock clock = Clock.systemDefaultZone();
    private static final ZoneId zoneId = ZoneId.systemDefault();

    /**
     * @MethodName now
     * @Author Hai.Ming
     * @Date 2021/5/27 19:53
     * @Description 获取当前系统时间, clock 指定
     **/
    public static LocalDateTime now() {
        return LocalDateTime.now(clock);
    }

    /**
     * @MethodName format
     * @Author Hai.Ming
     * @Date 2021/5/28 10:05
     * @Description 按指定格式转化指定 LocalDateTime 为 String
     **/
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @MethodName format
     * @Author Hai.Ming
     * @Date 2021/5/28 10:04
     * @Description 按指定格式转化指定 LocalDate 为 String
     **/
    public static String format(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @MethodName format
     * @Author Hai.Ming
     * @Date 2021/5/28 10:04
     * @Description 按指定格式转化当前时间为 String
     **/
    public static String format(String pattern) {
        return format(now(), pattern);
    }

    /**
     * @MethodName toLocalDate
     * @Author Hai.Ming
     * @Date 2021/5/27 19:53
     * @Description Date -> LocalDate
     **/
    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(zoneId).toLocalDate();
    }

    /**
     * @MethodName toLocalDate
     * @Author Hai.Ming
     * @Date 2021/5/30 16:23
     * @Description java.sql.Date -> LocalDate
     **/
    public static LocalDate toLocalDate(java.sql.Date date) {
        return date.toLocalDate();
    }

    /**
     * @MethodName toLocalDateTime
     * @Author Hai.Ming
     * @Date 2021/5/27 19:54
     * @Description Date -> LocalDateTime
     **/
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    /**
     * @MethodName toLocalDateTime
     * @Author Hai.Ming
     * @Date 2021/5/30 16:24
     * @Description java.sql.Date -> LocalDateTime
     **/
    public static LocalDateTime toLocalDateTime(java.sql.Date date) {
        return date.toLocalDate().atStartOfDay();
    }

    /**
     * @MethodName toDate
     * @Author Hai.Ming
     * @Date 2021/5/27 19:54
     * @Description localDateTime -> Date
     **/
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    /**
     * @MethodName toDate
     * @Author Hai.Ming
     * @Date 2021/5/27 20:40
     * @Description LocalDate -> Date
     **/
    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(zoneId).toInstant());
    }

    /**
     * @MethodName toDate
     * @Author Hai.Ming
     * @Date 2021/5/27 19:58
     * @Description LocalDateTime -> java.sql.Date
     **/
    public static java.sql.Date toSqlDate(LocalDateTime localDateTime) {
        long time = toDate(localDateTime).getTime();
        return new java.sql.Date(time);
    }

    /**
     * @MethodName toSqlDate
     * @Author Hai.Ming
     * @Date 2021/5/27 20:40
     * @Description LocalDate -> java.sql.Date
     **/
    public static java.sql.Date toSqlDate(LocalDate localDate) {
        long time = toDate(localDate).getTime();
        return new java.sql.Date(time);
    }

    /**
     * @MethodName toSqlTimestamp
     * @Author Hai.Ming
     * @Date 2021/5/27 20:51
     * @Description LocalDateTime -> java.sql.Timestamp
     **/
    public static java.sql.Timestamp toSqlTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(format(localDateTime, DATE_FORMATTER_YYYY_MM_DD_HH_MM_SS));
    }
}

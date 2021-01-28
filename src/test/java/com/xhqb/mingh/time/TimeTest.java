package com.xhqb.mingh.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;

/**
 * @ClassName TimeTest
 * @Author Hai.Ming
 * @Date 2021/1/27 8:53 下午
 * @Description 时间工具类测试
 */
@Slf4j
public class TimeTest {

    /**
     * @MethodName testLocalTime
     * @Author Hai.Ming
     * @Date 2021/1/28 8:58 下午
     * @Description test LocalTime API
     **/
    @Test
    public void testLocalTime() {
        // 获取当前系统时间
        LocalTime localTime = LocalTime.now();
        log.info("System current local time is {}", localTime);
    }

    /**
     * @MethodName testLocalDate
     * @Author Hai.Ming
     * @Date 2021/1/27 8:54 下午
     * @Description test LocalDate API
     **/
    @Test
    public void testLocalDate() {
        // 获取当前系统日期
        LocalDate localDate = LocalDate.now();
        log.info("System current local date is {}", localDate);
        // 获取当前系统时间
        LocalTime localTime = LocalTime.now();
        log.info("System current local time is {}", localTime);
        // 自定义日期
        LocalDate sefDefineDate1 = LocalDate.parse("2021-01-28");
        LocalDate sefDefineDate2 = LocalDate.of(2021, 1, 28);
        log.info("Self define local date is {}", sefDefineDate1);
        log.info("Self define local date is {}", sefDefineDate2);
        // 获取当天所属本年中的第几天
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        log.info("Day of week is {}", dayOfWeek);
        // 获取当天所属本星期中的第几天的值
        int dayOfWeekValue = LocalDate.now().getDayOfWeek().getValue();
        log.info("The value day of week is {}", dayOfWeekValue);
        // 获取当天所属本月中的第几天
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        log.info("Day of month is {}", dayOfMonth);
        // 获取当月属于本年第几月
        Month month = LocalDate.now().getMonth();
        log.info("Month of year is {}", month);
        // 获取当月属于本年第几月的值
        int monthValue = LocalDate.now().getMonth().getValue();  // getMonthValue()
        log.info("Month of year value is {}", monthValue);
        // 获取本月第几天
        LocalDate seventhLocalDate1 = LocalDate.now().withDayOfMonth(7);
        LocalDate seventhLocalDate2 = LocalDate.now().with(temporal -> temporal.with(DAY_OF_MONTH, 7));
        log.info("Seventh day of month is {}", seventhLocalDate1);
        log.info("Seventh day of month is {}", seventhLocalDate2);
        // 获取本月第一天和最后一天
        LocalDate firstLocalDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        log.info("First day of month is {}", firstLocalDate);
        LocalDate lastLocalDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        log.info("Last day of month is {}", lastLocalDate);
        // 获取前一天日期
        LocalDate preLocalDate = LocalDate.now().plusDays(-1);
        log.info("Pre day of month is {}", preLocalDate);
    }
}

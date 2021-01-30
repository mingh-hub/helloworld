package com.xhqb.mingh.time;

import com.xhqb.mingh.common.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.DAY_OF_YEAR;

/**
 * @ClassName TimeTest
 * @Author Hai.Ming
 * @Date 2021/1/27 8:53 下午
 * @Description 时间工具类测试
 */
@Slf4j
public class TimeTest {

    /**
     * @MethodName testLocalDateTime
     * @Author Hai.Ming
     * @Date 2021/1/29 8:20 下午
     * @Description test LocalDateTime API
     **/
    @Test
    public void testLocalDateTime() {
        // 时间获取
        log.info("===============local date time obtain=================");
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("current LocalDateTime is {}", localDateTime);
        LocalDateTime currentMonthFirstDay = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        log.info("first day of current month is {}", currentMonthFirstDay);
        LocalDateTime currentMonthLastDay = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
        log.info("last day of current month is {}", currentMonthLastDay);
        LocalDateTime currentYearFirstDay = LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear());
        log.info("first day of current year is {}", currentYearFirstDay);
        LocalDateTime currentYearLastDay = LocalDateTime.now().with(TemporalAdjusters.lastDayOfYear());
        log.info("last day of current year is {}", currentYearLastDay);
        LocalDateTime currentYearNextMonthFirstDay = LocalDateTime.now().with(TemporalAdjusters.firstDayOfNextMonth());
        log.info("first day of current year next month is {}", currentYearNextMonthFirstDay);
        LocalDateTime nextYearFirstDay = LocalDateTime.now().with(TemporalAdjusters.firstDayOfNextYear());
        log.info("first day of next year is {}", nextYearFirstDay);
        LocalDateTime seventhDayOfPreviousYear = LocalDateTime.now().with(temporal -> temporal.with(DAY_OF_YEAR, 7).plus(-1, ChronoUnit.YEARS));
        log.info("seventh day of previous year is {}", seventhDayOfPreviousYear);
        LocalDateTime nextFriday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        log.info("next friday is {}", nextFriday);
        // 时间转化
        log.info("===============local date time transfer=================");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FORMAT);
        String localDateTimeStr1 = LocalDateTime.now().format(formatter);
        String localDateTimeStr2 = formatter.format(LocalDateTime.now());
        log.info("System current LocalDateTime string localDateTimeStr1 is {}", localDateTimeStr1);
        log.info("System current LocalDateTime string localDateTimeStr2 is {}", localDateTimeStr2);
        // 时间增加
        log.info("===============plus  local date time=================");
        LocalDateTime plusLocalDateTimeWithPeriod = LocalDateTime.now().plus(Period.ofDays(10));
        log.info("plus LocalDateTime with period {}", plusLocalDateTimeWithPeriod);
        LocalDateTime plusLocalDateTimeWithUnit = LocalDateTime.now().plus(10, ChronoUnit.DAYS);
        log.info("plus LocalDateTime with unit {}", plusLocalDateTimeWithUnit);
        LocalDateTime plusLocalDateTimeWithYears = LocalDateTime.now().plusYears(10);
        log.info("plus LocalDateTime with year {}", plusLocalDateTimeWithYears);
        LocalDateTime plusLocalDateTimeWithDays = LocalDateTime.now().plusDays(10);
        log.info("plus LocalDateTime with day {}", plusLocalDateTimeWithDays);
        // 时间减少
        log.info("===============minus local date time=================");
        LocalDateTime minusLocalDateTimeWithPeriod = LocalDateTime.now().minus(Period.ofDays(10));
        log.info("minus LocalDateTime with period {}", minusLocalDateTimeWithPeriod);
        LocalDateTime minusLocalDateTimeWithUnit = LocalDateTime.now().minus(10, ChronoUnit.DAYS);
        log.info("minus LocalDateTime with unit {}", minusLocalDateTimeWithUnit);
        LocalDateTime minusLocalDateTimeWithYears = LocalDateTime.now().minusYears(10);
        log.info("minus LocalDateTime with year {}", minusLocalDateTimeWithYears);
        LocalDateTime minusLocalDateTimeWithDays = LocalDateTime.now().minusDays(10);
        log.info("minus LocalDateTime with day {}", minusLocalDateTimeWithDays);
        // 时间比较
        log.info("===============compare local date time=================");
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        LocalDateTime nextLocalDateTime = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
        log.info("currentLocalDateTime compare to nextLocalDateTime {}", currentLocalDateTime.compareTo(nextLocalDateTime));
        log.info("currentLocalDateTime is before nextLocalDateTime {}", currentLocalDateTime.isBefore(nextLocalDateTime));
        log.info("currentLocalDateTime is after nextLocalDateTime {}", currentLocalDateTime.isAfter(nextLocalDateTime));
    }

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
        // 获取当前系统时间并转为 String
        String localTimeStr = LocalTime.now().format(DateTimeFormatter.ofPattern(CommonConstants.TIME_FORMAT));
        log.info("System current local time string is {}", localTimeStr);
        // 获取时, 分, 秒
        int hour = LocalTime.now().getHour();
        int minute = LocalTime.now().getMinute();
        int second = LocalTime.now().getSecond();
        log.info("Current time hour is {}, minute is {}, second is {}", hour, minute, second);
        // 自定义时间
        LocalTime startTime = LocalTime.of(01, 0, 0);
        LocalTime endTime = LocalTime.parse("12:00:00");
        log.info("Self define local start time is {}", startTime);
        log.info("Self define local end time is {}", endTime);
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

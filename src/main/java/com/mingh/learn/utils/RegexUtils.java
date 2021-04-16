package com.mingh.learn.utils;

import com.mingh.learn.common.enums.CommonEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @ClassName RegexUtils
 * @Author Hai.Ming
 * @Date 2021/2/4 9:27 下午
 * @Description 正则表达式工具类
 */
public class RegexUtils {

    /**
     * @MethodName isEmail
     * @Author Hai.Ming
     * @Date 2021/2/13 2:55 下午
     * @Description 邮箱校验
     **/
    public static boolean isEmail(String email) {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Optional.ofNullable(email)
                .filter(StringUtils::isNotBlank)
                .map(source -> match(emailRegex, source))
                .orElse(Boolean.FALSE);
    }

    /**
     * @MethodName isIdNumber
     * @Author Hai.Ming
     * @Date 2021/2/13 2:25 下午
     * @Description 校验身份证号
     **/
    public static boolean isIdNumber(String idNo) {
        return isId18Number(idNo) || isId15Number(idNo);
    }

    /**
     * @MethodName isId18Number
     * @Author Hai.Ming
     * @Date 2021/2/13 2:36 下午
     * @Description 校验 18 位身份证号, 假设 18 位身份证号码为 41000119910101123X
     *                      410001 19910101 123X
     *                      ^: 开头
     *                      [1-9]: 第一位为 1-9 中的一个
     *                      \\d{5}: 五位数字, 前六位为市县地区
     *                      (18|19|20): 年份前两位, 现阶段取值范围为 18xx-20xx 年
     *                      \\d{2}: 年份后两位
     *                      ((0[1-9])|(10|11|12)): 月份
     *                      (([0-2][1-9])|10|20|30|31): 日期
     *                      \\d{3}: 三位数字, 身份证第 17 位奇数代表男, 偶数代表女
     *                      [0-9Xx]: 第十八位为校验值, 可为 0-9Xx 中的一个
     *                      $: 结束
     **/
    public static boolean isId18Number(String idNo) {
        String id18Regex = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        return Optional.ofNullable(idNo)
                .filter(StringUtils::isNotBlank)
                .map(no -> match(id18Regex, no))
                .orElse(Boolean.FALSE);
    }

    /**
     * @MethodName isId15Number
     * @Author Hai.Ming
     * @Date 2021/2/13 2:28 下午
     * @Description 校验 15 位身份证号, 假设 15 位身份证号码为 410001910101123
     *                      410001 910101 123
     *                      ^: 开头
     *                      [1-9]: 第一位为 1-9 中的一个
     *                      \\d{5}: 五位数字, 前六位为市县地区
     *                      \\d{2}: 年份后两位
     *                      ((0[1-9])|(10|11|12)): 月份
     *                      (([0-2][1-9])|10|20|30|31): 日期
     *                      \\d{3}: 三位数字, 不含 X|x, 末位奇数代表男, 偶数代表女
     *                      $: 结束
     **/
    public static boolean isId15Number(String idNo) {
        String id15Regex = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$";
        return Optional.ofNullable(idNo)
                .filter(StringUtils::isNotBlank)
                .map(no -> match(id15Regex, no))
                .orElse(Boolean.FALSE);
    }

    /**
     * @MethodName isPhoneNo
     * @Author Hai.Ming
     * @Date 2021/2/13 2:08 下午
     * @Description 校验手机号
     **/
    public static boolean isPhoneNo(String phoneNo) {
        String phoneRegex = "^[1]\\d{10}$";
        return Optional.ofNullable(phoneNo)
                .filter(StringUtils::isNotBlank)
                .map(str -> RegexUtils.match(phoneRegex, str))
                .orElse(Boolean.FALSE);
    }

    /**
     * @MethodName match
     * @Author Hai.Ming
     * @Date 2021/2/4 9:29 下午
     * @Description 正则匹配
     **/
    public static boolean match(String regex, String matcher) {
        return Pattern.compile(regex).matcher(matcher).matches();
    }

    /**
     * @MethodName replaceAll
     * @Author Hai.Ming
     * @Date 2021/2/12 8:30 下午
     * @Description 字符串替换
     **/
    public static String replaceAll(String regex, String source, String target) {
        return Optional.ofNullable(source)
                .filter(StringUtils::isNotBlank)
                .map(str -> replaceAll(CommonEnum.Regex.WHITESPACE.getRegex(), str, StringUtils.EMPTY))
                .map(str -> str.replaceAll(regex, target))
                .orElse(StringUtils.EMPTY);
    }

    /**
     * @MethodName split
     * @Author Hai.Ming
     * @Date 2021/2/12 10:21 下午
     * @Description 字符串分割
     **/
    public static String[] split(String regex, String source) {
        return Optional.ofNullable(source)
                .filter(StringUtils::isNotBlank)
                .map(str -> replaceAll(CommonEnum.Regex.WHITESPACE.getRegex(), str, StringUtils.EMPTY))
                .map(str -> str.split(regex))
                .orElse(ArrayUtils.toArray(StringUtils.EMPTY));
    }
}

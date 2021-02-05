package com.xhqb.mingh.utils;

import java.util.regex.Pattern;

/**
 * @ClassName RegexUtils
 * @Author Hai.Ming
 * @Date 2021/2/4 9:27 下午
 * @Description 正则表达式工具类
 */
public class RegexUtils {

    /**
     * @MethodName match
     * @Author Hai.Ming
     * @Date 2021/2/4 9:29 下午
     * @Description 正则匹配
     **/
    public static boolean match(String regex, String matcher) {
        return Pattern.compile(regex).matcher(matcher).matches();
    }
}

package com.xhqb.mingh.regex;

import com.xhqb.mingh.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RegexTest
 * @Author Hai.Ming
 * @Date 2021/2/3 8:26 下午
 * @Description 正则相关知识点测试
 */
@Slf4j
public class RegexTest {

    /**
     * @MethodName testBasicRegex
     * @Author Hai.Ming
     * @Date 2021/2/4 9:44 下午
     * @Description 基础信息测试
     **/
    @Test
    public void testBasicRegex() {
        log.info("===============simple class regex===============");
        // [abc] a, b, or c (simple class), match single character
        String simpleRegex = "[abc]";
        boolean simpleRegexFlag1 = RegexUtils.match(simpleRegex, "a");
        boolean simpleRegexFlag2 = RegexUtils.match(simpleRegex, "abc");
        log.info("simple class regex result1 is {}", simpleRegexFlag1);
        log.info("simple class regex result2 is {}", simpleRegexFlag2);

        log.info("===============negation regex===============");
        // [^abc] any character except a, b or c
        String simpleExceptRegex = "[^abc]";
        boolean simpleExceptFlag1 = RegexUtils.match(simpleExceptRegex, "a");
        boolean simpleExceptFlag2 = RegexUtils.match(simpleExceptRegex, "d");
        boolean simpleExceptFlag3 = RegexUtils.match(simpleExceptRegex, "def");
        log.info("negation regex result1 is {}", simpleExceptFlag1);
        log.info("negation regex result2 is {}", simpleExceptFlag2);
        log.info("negation regex result3 is {}", simpleExceptFlag3);

        log.info("===============range regex===============");
        // [a-zA-Z] test single lowercase or uppercase letter
        String singleLetterRegex = "[a-zA-Z]";
        boolean allLetterFlag1 = RegexUtils.match(singleLetterRegex, "a");
        boolean allLetterFlag2 = RegexUtils.match(singleLetterRegex, "ab");
        boolean allLetterFlag3 = RegexUtils.match(singleLetterRegex, "1");
        log.info("range regex result1 is {}", allLetterFlag1);
        log.info("range regex result2 is {}", allLetterFlag2);
        log.info("range regex result3 is {}", allLetterFlag3);

        log.info("===============union regex===============");
        // [a-d[m-p]] a through d, or m through p: [a-dm-p]
        String unionRegex = "[a-dm-p]";
        boolean unionFlag1 = RegexUtils.match(unionRegex, "a");
        boolean unionFlag2 = RegexUtils.match(unionRegex, "N");
        boolean unionFlag3 = RegexUtils.match(unionRegex, "z");
        log.info("union regex result1 is {}", unionFlag1);
        log.info("union regex result2 is {}", unionFlag2);
        log.info("union regex result3 is {}", unionFlag3);

        log.info("===============intersection regex===============");
        // [a-z&&[def]] d, e, or f
        String intersectionRegex = "[a-z&&[def]]";
        boolean intersectionFlag1 = RegexUtils.match(intersectionRegex, "b");
        boolean intersectionFlag2 = RegexUtils.match(intersectionRegex, "d");
        boolean intersectionFlag3 = RegexUtils.match(intersectionRegex, "de");
        log.info("intersection regex result1 is {}", intersectionFlag1);
        log.info("intersection regex result2 is {}", intersectionFlag2);
        log.info("intersection regex result3 is {}", intersectionFlag3);

        log.info("===============subtraction regex===============");
        // [a-z&&[^bc]] a through z, except for b and c, equals [ad-z]
        String subtractionRegex = "[a-z&&[^bc]]";
        boolean subtractionFlag1 = RegexUtils.match(subtractionRegex, "a");
        boolean subtractionFlag2 = RegexUtils.match(subtractionRegex, "b");
        log.info("subtraction regex result1 is {}", subtractionFlag1);
        log.info("subtraction regex result2 is {}", subtractionFlag2);

    }

    /**
     * @MethodName testSomeRegex
     * @Author Hai.Ming
     * @Date 2021/2/3 8:38 下午
     * @Description 正则相关信息
     **/
    @Test
    public void testSomeRegex() {
        /**
         * . 也是一个正则表达式, 表示匹配任意一个字符: "a" 或 "1"
         * \. 表示匹配一个点, 具有特定含义的字符匹配需要进行转义
         * ^ 定义以什么开始匹配, 比如 ^\d+ 表示以一个或多个数字开始
         * ? 是设置括号内的表达式是可匹配的, 当然也可不匹配, (\.\d+)? 则表示可以匹配 . 或者 .0
         * * 表示 0-无穷
         * + 表示 1-无穷
         **/
        log.info("===============any one character regex===============");
        // 就表示任意一个字符, 为空或者长度大于一的字符串都返回 false
        String anyOneCharacterRegex = ".";
        Pattern anyOneCharacterPattern = Pattern.compile(anyOneCharacterRegex);
        Matcher anyOneCharacterMatcher1 = anyOneCharacterPattern.matcher("");
        Matcher anyOneCharacterMatcher2 = anyOneCharacterPattern.matcher("a");
        Matcher anyOneCharacterMatcher3 = anyOneCharacterPattern.matcher("aa");
        log.info("any one character match anyOneCharacterMatcher1 is {}", anyOneCharacterMatcher1.matches());
        log.info("any one character match anyOneCharacterMatcher2 is {}", anyOneCharacterMatcher2.matches());
        log.info("any one character match anyOneCharacterMatcher3 is {}", anyOneCharacterMatcher3.matches());

        log.info("===============one point regex===============");
        // 具有特殊含义的正则表达式如果想表达本意则需使用 \\ 进行转义, 如下只匹配一个 "."
        String pointRegex = "\\.";
        Pattern pointPattern = Pattern.compile(pointRegex);
        Matcher pointMatcher1 = pointPattern.matcher("");
        Matcher pointMatcher2 = pointPattern.matcher(".");
        Matcher pointMatcher3 = pointPattern.matcher("1");
        Matcher pointMatcher4 = pointPattern.matcher(".1");
        log.info("one point match pointMatcher1 is {}", pointMatcher1.matches());
        log.info("one point match pointMatcher2 is {}", pointMatcher2.matches());
        log.info("one point match pointMatcher3 is {}", pointMatcher3.matches());
        log.info("one point match pointMatcher4 is {}", pointMatcher4.matches());

        log.info("===============point and star regex===============");
        // .* 表示匹配 0-无穷 字符
        String pointAndStarRegex = ".*abc.*";
        Pattern pointAndStarPattern = Pattern.compile(pointAndStarRegex);
        Matcher pointAndStarMatcher1 = pointAndStarPattern.matcher("this is google come abcde");
        Matcher pointAndStarMatcher2 = pointAndStarPattern.matcher("abcde");
        Matcher pointAndStarMatcher3 = pointAndStarPattern.matcher("  abcde");
        log.info("one point match pointAndStarMatcher1 is {}, group count is {}", pointAndStarMatcher1.matches(), pointAndStarMatcher1.groupCount());
        log.info("one point match pointAndStarMatcher2 is {}, group count is {}", pointAndStarMatcher2.matches(), pointAndStarMatcher2.groupCount());
        log.info("one point match pointAndStarMatcher3 is {}, group count is {}", pointAndStarMatcher3.matches(), pointAndStarMatcher3.groupCount());

        log.info("===============capture group regex===============");
        // 捕获组, 把多个字符当一个单独单元进行处理的方法, 它通过对括号内的字符分组来创建
        String captureRegex = "(\\D*)(\\d+)(.*)";
        String regexStr = "This order was placed for QT3000! OK?";
        Pattern capturePattern = Pattern.compile(captureRegex);
        Matcher captureMatcher = capturePattern.matcher(regexStr);
        log.info("capture group match captureMatcher is {}, group count is {}", captureMatcher.matches(), captureMatcher.groupCount());
    }
}

package com.mingh.learn.regex;

import com.mingh.learn.utils.RegexUtils;
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
     * @MethodName testPredefinedCharacterClasses
     * @Author Hai.Ming
     * @Date 2021/2/12 3:35 下午
     * @Description test predefined character classes
     **/
    @Test
    public void testPredefinedCharacterClasses() {
        log.info("===============any character except line terminators regex===============");
        // .: Any one character (may or may not match line terminators), use .* match one or more character
        String pointRegex = ".";
        boolean pointMatchFlag1 = RegexUtils.match(pointRegex, "a");
        log.info("a character match result is {}", pointMatchFlag1);
        boolean pointMatchFlag2 = RegexUtils.match(pointRegex, " ");
        log.info("space character match result is {}", pointMatchFlag2);
        boolean pointMatchFlag3 = RegexUtils.match(pointRegex, "\n");
        log.info("line terminators character match result is {}", pointMatchFlag3);

        log.info("===============any character regex===============");
        // [\s\S]: any one character, except empty string
        // any character: 匹配任意字符, 但是不包含空字符串
        String anyCharacterRegex = "[\\s\\S]";
        boolean anyCharacterMatchFlag0 = RegexUtils.match(anyCharacterRegex, "");
        log.info("any character match empty string result is {}", anyCharacterMatchFlag0);
        boolean anyCharacterMatchFlag1 = RegexUtils.match(anyCharacterRegex, "a");
        log.info("any character match 'a' result is {}", anyCharacterMatchFlag1);
        boolean anyCharacterMatchFlag2 = RegexUtils.match(anyCharacterRegex, " ");
        log.info("any character match space result is {}", anyCharacterMatchFlag2);
        boolean anyCharacterMatchFlag3 = RegexUtils.match(anyCharacterRegex, "\n");
        log.info("any character match line terminators result is {}", anyCharacterMatchFlag3);

        log.info("===============a digit character regex===============");
        // \d: a digit equals [0-9]
        String digitRegex = "\\d";
        boolean digitMatchFlag1 = RegexUtils.match(digitRegex, "1");
        log.info("digit regex match 1 result is {}", digitMatchFlag1);
        boolean digitMatchFlag2 = RegexUtils.match(digitRegex, "a");
        log.info("digit regex match a result is {}", digitMatchFlag2);

        log.info("===============a non-digit character regex===============");
        // \D: a non-digit regex equals [^0-9]
        String nonDigitRegex = "\\D";
        boolean nonDigitMatchFlag1 = RegexUtils.match(nonDigitRegex, "1");
        log.info("non-digit regex match 1 result is {}", nonDigitMatchFlag1);
        boolean nonDigitMatchFlag2 = RegexUtils.match(nonDigitRegex, "a");
        log.info("non-digit regex match a result is {}", nonDigitMatchFlag2);

        log.info("===============a horizontal whitespace character regex===============");
        // \h: match a horizontal whitespace character, equals  [ \t\xA0\u1680\u180e\u2000-\u200a\u202f\u205f\u3000]
        // horizontal whitespace: 可以理解为空格键
        String horizontalWhitespaceRegex = "\\h";
        boolean horizontalMatchFlag1 = RegexUtils.match(horizontalWhitespaceRegex, " ");
        log.info("horizontal whitespace regex match one whitespace result is {}", horizontalMatchFlag1);
        boolean horizontalMatchFlag2 = RegexUtils.match(horizontalWhitespaceRegex, "  ");
        log.info("horizontal whitespace regex match two whitespace result is {}", horizontalMatchFlag2);

        log.info("===============a non-horizontal whitespace character regex===============");
        // \H: a non-horizontal whitespace character, equals [^\h]
        String nonHorizontalWhitespaceRegex = "\\H";
        boolean nonHorizontalMatchFlag1 = RegexUtils.match(nonHorizontalWhitespaceRegex, " ");
        log.info("non-horizontal whitespace regex match one whitespace result is {}", nonHorizontalMatchFlag1);
        boolean nonHorizontalMatchFlag2 = RegexUtils.match(nonHorizontalWhitespaceRegex, "1");
        log.info("non-horizontal whitespace regex match 1 result is {}", nonHorizontalMatchFlag2);

        log.info("===============a vertical whitespace character regex===============");
        // \v: a vertical whitespace character, equals [\n\x0B\f\r\x85\u2028\u2029]
        String verticalWhiteSpaceRegex = "\\v";
        boolean emptyStringMatchFlag0 = RegexUtils.match(verticalWhiteSpaceRegex, "");
        log.info("a vertical whitespace character regex match empty string result is {}", emptyStringMatchFlag0);
        boolean horizontalWhiteSpaceMatchFlag = RegexUtils.match(verticalWhiteSpaceRegex, " ");
        log.info("a vertical whitespace character regex match horizontal whitespace result is {}", horizontalWhiteSpaceMatchFlag);
        boolean verticalWhitespace = RegexUtils.match(verticalWhiteSpaceRegex, "\n");
        log.info("a vertical whitespace character regex match vertical whitespace result is {}", verticalWhitespace);

        log.info("===============a non-vertical whitespace character regex===============");
        // \V: a non-vertical whitespace character, equals [^\v]
        String nonVerticalWhiteSpaceRegex = "\\V";
        boolean emptyStringMatchFlag1 = RegexUtils.match(nonVerticalWhiteSpaceRegex, "");
        log.info("a non-vertical whitespace character regex match empty string result is {}", emptyStringMatchFlag1);
        boolean horizontalWhiteSpaceMatchFlag1 = RegexUtils.match(nonVerticalWhiteSpaceRegex, " ");
        log.info("a non-vertical whitespace character regex match horizontal whitespace result is {}", horizontalWhiteSpaceMatchFlag1);
        boolean verticalWhitespace1 = RegexUtils.match(nonVerticalWhiteSpaceRegex, "\n");
        log.info("a non-vertical whitespace character regex match vertical whitespace result is {}", verticalWhitespace1);
        
        log.info("===============a whitespace character regex===============");
        // \s: a whitespace character
        // whitespace: 任意空白字符, 如 水平空格, 回车符, 换行符等, 但不包含空字符串 "", 空字符串可用 .* 匹配到
        String whitespaceRegex = "\\s";
        boolean emptyStringMatchFlag = RegexUtils.match(whitespaceRegex, "");
        log.info("whitespace regex match empty string result is {}", emptyStringMatchFlag);
        boolean horizontalWhitespaceMatchFlag = RegexUtils.match(whitespaceRegex, " ");
        log.info("whitespace regex match a horizontal whitespace character result is {}", horizontalWhitespaceMatchFlag);
        boolean lineBreakMatchFlag = RegexUtils.match(whitespaceRegex, "\n");
        log.info("whitespace regex match a line break result is {}", lineBreakMatchFlag);

        log.info("===============a non-whitespace character regex===============");
        // \S: a non-whitespace character
        // non-whitespace: 非空格字符, 不包含空字符串 "", 空字符串可用 .* 匹配
        String nonWhitespaceRegex = "\\S";
        boolean nonEmptyStringMatchFlag = RegexUtils.match(nonWhitespaceRegex, "a");
        log.info("non-whitespace regex match empty string result is {}", nonEmptyStringMatchFlag);
        boolean nonHorizontalWhitespaceMatchFlag = RegexUtils.match(nonWhitespaceRegex, " ");
        log.info("non-whitespace regex match a horizontal whitespace character result is {}", nonHorizontalWhitespaceMatchFlag);
        boolean nonLineBreakMatchFlag = RegexUtils.match(nonWhitespaceRegex, "\n");
        log.info("non-whitespace regex match a line break result is {}", nonLineBreakMatchFlag);

        log.info("===============a word character regex===============");
        // \w: match a world, equals [a-zA-Z_0-9]
        // word character: 匹配单个英文字母数字及下划线
        String wordRegex = "\\w";
        boolean lowerEnglishAlphabetMatchFlag = RegexUtils.match(wordRegex, "a");
        log.info("a word character regex match a lower english alphabet result is {}", lowerEnglishAlphabetMatchFlag);
        boolean upperEnglishAlphabetMatchFlag = RegexUtils.match(wordRegex, "A");
        log.info("a word character regex match a upper english alphabet result is {}", upperEnglishAlphabetMatchFlag);
        boolean underlineMatchFlag = RegexUtils.match(wordRegex, "_");
        log.info("a word character regex match underline result is {}", underlineMatchFlag);
        boolean digitMatchFlag = RegexUtils.match(wordRegex, "7");
        log.info("a word character regex match a digit result is {}", digitMatchFlag);

        log.info("===============a non-word character regex===============");
        // \W: match a world, equals [^\w]
        String nonWordRegex = "\\W";
        boolean lowerEnglishAlphabetMatchFlag1 = RegexUtils.match(nonWordRegex, "a");
        log.info("a non-word character regex match a lower english alphabet result is {}", lowerEnglishAlphabetMatchFlag1);
        boolean upperEnglishAlphabetMatchFlag1 = RegexUtils.match(nonWordRegex, "A");
        log.info("a non-word character regex match a upper english alphabet result is {}", upperEnglishAlphabetMatchFlag1);
        boolean underlineMatchFlag1 = RegexUtils.match(nonWordRegex, "_");
        log.info("a non-word character regex match underline result is {}", underlineMatchFlag1);
        boolean digitMatchResult = RegexUtils.match(nonWordRegex, "7");
        log.info("a word character regex match a digit result is {}", digitMatchResult);
    }

    /**
     * @MethodName testCharacterClasses
     * @Author Hai.Ming
     * @Date 2021/2/4 9:44 下午
     * @Description test character classes
     **/
    @Test
    public void testCharacterClasses() {
        log.info("===============simple class regex===============");
        // [abc] a, b, or c (simple class), match single character
        String simpleRegex = "[abc]";
        boolean simpleMatchFlag1 = RegexUtils.match(simpleRegex, "a");
        log.info("simple class regex result1 is {}", simpleMatchFlag1);
        boolean simpleMatchFlag2 = RegexUtils.match(simpleRegex, "abc");
        log.info("simple class regex result2 is {}", simpleMatchFlag2);

        log.info("===============negation regex===============");
        // [^abc] any character except a, b or c
        String simpleExceptRegex = "[^abc]";
        boolean simpleExceptFlag1 = RegexUtils.match(simpleExceptRegex, "a");
        log.info("negation regex result1 is {}", simpleExceptFlag1);
        boolean simpleExceptFlag2 = RegexUtils.match(simpleExceptRegex, "d");
        log.info("negation regex result2 is {}", simpleExceptFlag2);
        boolean simpleExceptFlag3 = RegexUtils.match(simpleExceptRegex, "def");
        log.info("negation regex result3 is {}", simpleExceptFlag3);

        log.info("===============range regex===============");
        // [a-zA-Z] test single lowercase or uppercase letter
        String singleLetterRegex = "[a-zA-Z]";
        boolean allLetterFlag1 = RegexUtils.match(singleLetterRegex, "a");
        log.info("range regex result1 is {}", allLetterFlag1);
        boolean allLetterFlag2 = RegexUtils.match(singleLetterRegex, "ab");
        log.info("range regex result2 is {}", allLetterFlag2);
        boolean allLetterFlag3 = RegexUtils.match(singleLetterRegex, "1");
        log.info("range regex result3 is {}", allLetterFlag3);

        log.info("===============union regex===============");
        // [a-d[m-p]] a through d, or m through p: [a-dm-p]
        String unionRegex = "[a-dm-p]";
        boolean unionFlag1 = RegexUtils.match(unionRegex, "a");
        log.info("union regex result1 is {}", unionFlag1);
        boolean unionFlag2 = RegexUtils.match(unionRegex, "N");
        log.info("union regex result2 is {}", unionFlag2);
        boolean unionFlag3 = RegexUtils.match(unionRegex, "z");
        log.info("union regex result3 is {}", unionFlag3);

        log.info("===============intersection regex===============");
        // [a-z&&[def]]  d, e, or f
        String intersectionRegex = "[a-z&&[def]]";
        boolean intersectionFlag1 = RegexUtils.match(intersectionRegex, "b");
        log.info("intersection regex result1 is {}", intersectionFlag1);
        boolean intersectionFlag2 = RegexUtils.match(intersectionRegex, "d");
        log.info("intersection regex result2 is {}", intersectionFlag2);
        boolean intersectionFlag3 = RegexUtils.match(intersectionRegex, "de");
        log.info("intersection regex result3 is {}", intersectionFlag3);

        log.info("===============subtraction regex===============");
        // [a-z&&[^bc]] a through z, except for b and c, equals [ad-z]
        String subtractionRegex = "[a-z&&[^bc]]";
        boolean subtractionFlag1 = RegexUtils.match(subtractionRegex, "a");
        log.info("subtraction regex result1 is {}", subtractionFlag1);
        boolean subtractionFlag2 = RegexUtils.match(subtractionRegex, "b");
        log.info("subtraction regex result2 is {}", subtractionFlag2);

        // [a-z&&[^m-p]] a through z, and not m through p, equals [a-lq-z]
        String subtractionRangeRegex = "[a-z&&[^m-p]]";
        boolean subtractionRangeFlag1 = RegexUtils.match(subtractionRangeRegex, "a");
        log.info("subtraction range regex result1 is {}", subtractionRangeFlag1);
        boolean subtractionRangeFlag2 = RegexUtils.match(subtractionRangeRegex, "n");
        log.info("subtraction range regex result2 is {}", subtractionRangeFlag2);
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
        // 具有特殊含义的正则表达式如果想表达本意则需使用 \ 进行转义, 如下只匹配一个 "."
        String pointRegex = "\\.";
        Pattern pointPattern = Pattern.compile(pointRegex);
        Matcher pointMatcher1 = pointPattern.matcher("");
        log.info("one point match pointMatcher1 is {}", pointMatcher1.matches());
        Matcher pointMatcher2 = pointPattern.matcher(".");
        log.info("one point match pointMatcher2 is {}", pointMatcher2.matches());
        Matcher pointMatcher3 = pointPattern.matcher("1");
        log.info("one point match pointMatcher3 is {}", pointMatcher3.matches());
        Matcher pointMatcher4 = pointPattern.matcher(".1");
        log.info("one point match pointMatcher4 is {}", pointMatcher4.matches());

        log.info("===============point and star regex===============");
        // .* 表示匹配 0-无穷 字符
        String pointAndStarRegex = ".*abc.*";
        Pattern pointAndStarPattern = Pattern.compile(pointAndStarRegex);
        Matcher pointAndStarMatcher1 = pointAndStarPattern.matcher("this is google come abcde");
        log.info("one point match pointAndStarMatcher1 is {}, group count is {}", pointAndStarMatcher1.matches(), pointAndStarMatcher1.groupCount());
        Matcher pointAndStarMatcher2 = pointAndStarPattern.matcher("abcde");
        log.info("one point match pointAndStarMatcher2 is {}, group count is {}", pointAndStarMatcher2.matches(), pointAndStarMatcher2.groupCount());
        Matcher pointAndStarMatcher3 = pointAndStarPattern.matcher("  abcde");
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

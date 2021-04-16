package com.mingh.learn.regex;

import com.mingh.learn.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @ClassName RegexUtilsTest
 * @Author Hai.Ming
 * @Date 2021/2/13 2:09 下午
 * @Description 正则表达式工具测试
 */
@Slf4j
public class RegexUtilsTest {

    /**
     * @MethodName testEmailRegex
     * @Author Hai.Ming
     * @Date 2021/2/13 9:48 下午
     * @Description 测试邮箱的正则表达校验
     **/
    @Test
    public void testEmailRegex() {
        String email = "hdshu_jdisj_88@163.com";
        boolean emailResult = RegexUtils.isEmail(email);
        log.info("email validate result is {}", emailResult);
    }

    /**
     * @MethodName testIdNoRegex
     * @Author Hai.Ming
     * @Date 2021/2/13 2:48 下午
     * @Description 测试是否为身份证号
     **/
    @Test
    public void testIdNoRegex(){
        String id15No = "410001910101123";
        boolean id15NoResult = RegexUtils.isId15Number(id15No);
        log.info("id 15 no validate result is {}", id15NoResult);

        String id18No = "41000119910101123x";
        boolean id18NoResult = RegexUtils.isId18Number(id18No);
        log.info("id 18 no validate result is {}", id18NoResult);
    }

    /**
     * @MethodName testPhoneNoRegex
     * @Author Hai.Ming
     * @Date 2021/2/13 2:10 下午
     * @Description 测试是否为手机号
     **/
    @Test
    public void testPhoneNoRegex() {
        String phoneNo = "1364567891p";
        boolean result = RegexUtils.isPhoneNo(phoneNo);
        log.info("phoneNo regex validate result is {}", result);
    }
}

package com.mingh.learn.io;

import org.junit.jupiter.api.Test;

/**
 * @ClassName OutputStreamLearnTest
 * @Author Hai.Ming
 * @Date 2021/5/31 20:39
 * @Description test OutputStream
 */
public class OutputStreamLearnTest {

    /**
     * @MethodName testOutputWithAppend
     * @Author Hai.Ming
     * @Date 2021/5/31 20:43
     * @Description 测试利用 OutputStream 进行追加输出
     **/
    @Test
    public void testOutputWithAppend() {
        String filePath = "/Users/user/Desktop/test.txt";
        OutputStreamLearn learn = new OutputStreamLearn();
        learn.outputWithAppend(filePath);
    }

    /**
     * @MethodName testOutputWithOverride
     * @Author Hai.Ming
     * @Date 2021/5/31 20:40
     * @Description 测试利用 OutputStream 进行覆盖输出
     **/
    @Test
    public void testOutputWithOverride() {
        String filePath = "/Users/user/Desktop/my.txt";
        OutputStreamLearn learn = new OutputStreamLearn();
        learn.outputWithOverride(filePath);
    }
}

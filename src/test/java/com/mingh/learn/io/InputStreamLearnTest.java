package com.mingh.learn.io;

import org.junit.jupiter.api.Test;

/**
 * @ClassName InputStreamLearnTest
 * @Author Hai.Ming
 * @Date 2021/5/31 20:49
 * @Description test InputStreamLearn
 */
public class InputStreamLearnTest {

    /**
     * @MethodName testCopy
     * @Author Hai.Ming
     * @Date 2021/5/31 20:50
     * @Description 测试利用字节流进行文件复制
     **/
    @Test
    public void testCopy() {
        String sourcePath = "/Users/user/Pictures/head1.jpg";
        String targetPath = "/Users/user/Desktop/head1.jpg";
        InputStreamLearn learn = new InputStreamLearn();
        learn.copy(sourcePath, targetPath);
    }
}
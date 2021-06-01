package com.mingh.learn.io;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName WriterLearn
 * @Author Hai.Ming
 * @Date 2021/6/1 20:10
 * @Description Writer 是字符输出流的抽象基类
 *                          1. Writer 是进行字符输出操作时使用的类, 这个类是抽象类; 之所以使用这个类主要是因为这个类有个很好用的方法
 *                              |-- public void write(String str, int off,int len)throws IOException
 *                          2. 虽然 Writer 类提供有字符数组输出的能力, 但是从本质来说, 使用 Writer 类就意味着执行字符串的直接输出
 *                          3. 字符流是最适合操作中文的, 但并不意味着字节流就无法操作中文
 */
@Slf4j
public class WriterLearn {

    /**
     * @MethodName out
     * @Author Hai.Ming
     * @Date 2021/6/1 20:28
     * @Description 输出字符到指定文件
     **/
    public void outString(String filePath, String content) {
        FileWriter writer = null;
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            writer = new FileWriter(file);
            writer.write(content);
        } catch (Exception e) {
            log.error("字符输出异常", e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                log.error("FileWriter close failed. ", e);
            }
        }
    }
}

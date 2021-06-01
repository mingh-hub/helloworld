package com.mingh.learn.io;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName OutputStreamLearn
 * @Author Hai.Ming
 * @Date 2021/5/31 20:21
 * @Description 字节输出流
 */
@Slf4j
public class OutputStreamLearn {

    private static final String HELLO_WORLD = "Hello World!";

    /**
     * @MethodName outputWithAppend
     * @Author Hai.Ming
     * @Date 2021/5/31 20:32
     * @Description 字符追加输出
     **/
    public void outputWithAppend(String filePath) {
        OutputStream output;
        try {
            File file = new File(filePath);
            // 父路径不存在时, 需要创建
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            output = new FileOutputStream(file, true);
            for (int i = 0; i < 10; i++) {
                output.write((HELLO_WORLD + "\n").getBytes(StandardCharsets.UTF_8));
            }
            output.close();
        } catch (Exception e) {
            log.error("file output error. ", e);
        }
    }

    /**
     * @MethodName outputWithOverride
     * @Author Hai.Ming
     * @Date 2021/5/31 20:27
     * @Description 数据覆盖输出
     **/
    public void outputWithOverride(String filePath) {
        OutputStream output;
        try {
            File file = new File(filePath);
            // 父路径不存在时, 需要创建
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            output = new FileOutputStream(file);
            byte[] data = "测试数据覆盖输出".getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < data.length; i++) {
                // 在调用 write() 方法时就会将字节输出到文本了
                output.write(data[i]);
            }

            output.close();
        } catch (Exception e) {
            log.error("file output error. ", e);
        }
    }
}

package com.mingh.learn.io;

import com.mingh.learn.utils.BusinessUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @ClassName FileDemoTest
 * @Author Hai.Ming
 * @Date 2021/5/30 18:10
 * @Description test FileDemo
 */
@Slf4j
public class FileDemoTest {

    /**
     * @MethodName testCreateFile
     * @Author Hai.Ming
     * @Date 2021/5/30 18:37
     * @Description 测试创建文件及相关问题说明
     **/
    @Test
    public void testCreateFile() {
        // 问题一: 文件路径的分隔符不要写死, 用 File.separator 替代, 要考虑分隔符的问题, 如 e:\test.txt 中 \t 表示制表符
        // 问题二: 文件的创建会存在延迟操作, 这种延迟操作很多时候是可以忽略掉的, 单也有一点不能忽略, 即刚刚删除了一个文件, 就有可能存在创建不了的情况
        // 问题三: 如果给定的文件目录不存在, 则文件无法创建。解决: 创建前先判断文件父路径是否存在, 不存在则先创建父路径 file.getParentFile().mkdirs()
        String filePath = "/Users/user/mingh/repository/study/helloworld/test.txt";
        FileDemo fileDemo = new FileDemo();
        fileDemo.createFile(BusinessUtils.filePathReplace(filePath));
    }
}

package com.mingh.learn.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 文件操作相关测试测试
 */
@Slf4j
public class FileUtilsTest {

    @Test
    public void testCreateFile() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = formatter.format(new Date());
        String srcFileFullName = "/Users/admin/Documents/repository/download/INFO_AUTHORIZATION_20210121000000136004.pdf";
        String targetFileFullName = "/Users/admin/Documents/repository/download/pdf_base64_" + date + ".jpg";

        FileUtils.createFile(srcFileFullName, targetFileFullName, Boolean.FALSE);
    }

}

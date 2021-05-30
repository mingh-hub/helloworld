package com.mingh.learn.io;

import com.mingh.learn.common.enums.ResultEnum;
import com.mingh.learn.common.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileDemo
 * @Author Hai.Ming
 * @Date 2021/5/30 17:50
 * @Description java.io.File相关操作
 */
@Slf4j
public class FileDemo {

    /**
     * @MethodName createFile
     * @Author Hai.Ming
     * @Date 2021/5/30 18:00
     * @Description 创建文件或目录
     **/
    public void createFile(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        File file = new File(filePath);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            log.info("是否为目录: {}", file.isDirectory());
            log.info("是否为文件: {}", file.isFile());
            file.createNewFile();
        } catch (IOException e) {
            log.error(ResultEnum.FILE_CREATE_FAILURE.getMsg(), e);
        }
    }
}

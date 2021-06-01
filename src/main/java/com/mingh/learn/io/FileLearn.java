package com.mingh.learn.io;

import com.mingh.learn.common.enums.ResultEnum;
import com.mingh.learn.common.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @ClassName FileLearn
 * @Author Hai.Ming
 * @Date 2021/5/30 17:50
 * @Description java.io.File相关操作
 */
@Slf4j
public class FileLearn {

    /**
     * @MethodName listAllFilesWithFilter
     * @Author Hai.Ming
     * @Date 2021/5/30 23:24
     * @Description 根据指定 filePath 列出该路径下指定后缀的文件
     **/
    public void listAllFilesWithFilter(String filePath, String suffix) {
        Objects.requireNonNull(filePath, suffix);
        File[] files = new File(filePath).listFiles(file -> file.exists() && file.isFile() && StringUtils.endsWith(file.getName(), suffix));
        assert files != null;
        Arrays.stream(files).forEach(file -> log.info("文件: {}", file.getAbsolutePath()));
    }

    /**
     * @MethodName listAllFiles
     * @Author Hai.Ming
     * @Date 2021/5/30 22:23
     * @Description 根据指定 filePath 列出其下所有 file 文件
     **/
    public void listAllFiles(String filePath) {
        Objects.requireNonNull(filePath);
        File[] files = new File(filePath).listFiles();
        if (Objects.isNull(files)) {
            return;
        }
        Arrays.stream(files).forEach(file -> {
            if (file.exists() && file.isFile()) {
                log.info("文件: {}", file.getAbsolutePath());
            } else {
                listAllFiles(file.getPath());
            }
        });
    }

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

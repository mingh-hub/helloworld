package com.mingh.learn.utils;

import com.mingh.learn.common.constant.CommonConstants;
import com.mingh.learn.common.enums.ResultEnum;
import com.mingh.learn.common.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Base64;
import java.util.Objects;

/**
 * 文件操作工具类
 */
@Slf4j
public class FileUtils {

    /**
     * 根据源文件全路径创建目标文件
     * @param srcFileFullName 源文件全路径名
     * @param targetFileFullName 目标文件全路径名
     * @return
     */
    public static boolean createFile(String srcFileFullName, String targetFileFullName) {
        return createFile(srcFileFullName, targetFileFullName, Boolean.FALSE);
    }

    /**
     * 根据源文件全路径创建目标文件
     *
     * @param srcFileFullName    源文件全路径名
     * @param targetFileFullName 目标文件全路径名
     * @param base64Flag         是否需要 BASE64 对源文件进行解码
     * @return
     */
    public static boolean createFile(String srcFileFullName, String targetFileFullName, boolean base64Flag) {
        if (StringUtils.isBlank(srcFileFullName) || StringUtils.isBlank(targetFileFullName)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        String srcFileName = srcFileFullName.substring(srcFileFullName.lastIndexOf(CommonConstants.SEPARATOR_FORWARD_SLASH) + 1);
        String srcFilePath = srcFileFullName.substring(0, srcFileFullName.lastIndexOf(CommonConstants.SEPARATOR_FORWARD_SLASH));
        String targetFileName = targetFileFullName.substring(targetFileFullName.lastIndexOf(CommonConstants.SEPARATOR_FORWARD_SLASH) + 1);
        String targetFilePath = targetFileFullName.substring(0, targetFileFullName.lastIndexOf(CommonConstants.SEPARATOR_FORWARD_SLASH));
        return createFile(srcFilePath, srcFileName, targetFilePath, targetFileName, base64Flag);
    }

    /**
     * 根据源文件路径创建新的文件
     * @param srcFilePath 源文件路径
     * @param srcFileName 源文件名
     * @param targetFilePath 目标文件路径
     * @param targetFileName 目标文件名
     * @return
     */
    public static boolean createFile(String srcFilePath, String srcFileName, String targetFilePath, String targetFileName) {
        return createFile(srcFilePath, srcFileName, targetFilePath, targetFileName, Boolean.FALSE);
    }

    /**
     * 根据源文件路径创建新的文件
     * @param srcFilePath    源文件路径
     * @param srcFileName    源文件名
     * @param targetFilePath 目标文件路径
     * @param targetFileName 目标文件名
     * @param base64Flag 是否需要 BASE64 对源文件进行解码
     * @return
     */
    public static boolean createFile(String srcFilePath, String srcFileName, String targetFilePath, String targetFileName, boolean base64Flag) {
        if (StringUtils.isBlank(srcFilePath) || StringUtils.isBlank(srcFileName) || StringUtils.isBlank(targetFilePath) || StringUtils.isBlank(targetFileName)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        byte[] srcData = getBytesByFilePath(srcFilePath, srcFileName, base64Flag);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        File file = null;
        try {
            File dir = new File(targetFilePath);
            // 判断文件目录是否存在
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(targetFilePath + File.separator + targetFileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(srcData);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("create file failed", e);
            return Boolean.FALSE;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error("bos close failed", e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("fos close failed", e);
                }
            }
        }
    }

    /**
     * 根据文件路径读取文件数据
     * @param fileFullName 文件全路径名
     * @return
     */
    public static byte[] getBytesByFilePath(String fileFullName) {
        if (StringUtils.isBlank(fileFullName)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        return getBytesByFilePath(fileFullName.substring(0, fileFullName.lastIndexOf(CommonConstants.SEPARATOR_FORWARD_SLASH)), fileFullName.substring(fileFullName.lastIndexOf(CommonConstants.SEPARATOR_FORWARD_SLASH) + 1), Boolean.FALSE);
    }

    /**
     * 根据文件路径读取文件数据
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param base64Flag 是否需要 BASE64 进行解码
     * @return
     */
    public static byte[] getBytesByFilePath(String filePath, String fileName, boolean base64Flag) {
        if (StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        File file = new File(filePath + File.separator + fileName);
        if (BooleanUtils.isFalse(file.exists())) {
            throw new BusinessRuntimeException(ResultEnum.FILE_PATH_NOT_FOUND);
        }
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("file not found exception", e);
            throw new BusinessRuntimeException(ResultEnum.FILE_NOT_FOUND);
        }
        return toByteArray(inputStream, base64Flag);
    }

    /**
     * InputStream转化为byte[]数组
     *
     * @param inputStream 输入源文件流
     * @param base64Flag 是否需要 BASE64 进行解码
     * @return
     */
    public static byte[] toByteArray(InputStream inputStream, boolean base64Flag) {
        if (Objects.isNull(inputStream)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        ByteArrayOutputStream output = null;
        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            output.flush();
        } catch (IOException e) {
            log.error("FileUtils toByteArray execute failed because of IOException", e);
            throw new BusinessRuntimeException(ResultEnum.IO_EXCEPTION);
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("FileUtils toByteArray inputStream close failed", e);
            }
            if (Objects.nonNull(output)) {
                try {
                    output.close();
                } catch (IOException e) {
                    log.error("FileUtils toByteArray output close failed", e);
                }
            }
        }
        return base64Flag ? Base64.getDecoder().decode(output.toByteArray()) : output.toByteArray();
    }
}

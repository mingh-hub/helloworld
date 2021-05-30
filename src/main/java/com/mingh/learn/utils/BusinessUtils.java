package com.mingh.learn.utils;

import com.mingh.learn.common.constant.CommonConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @ClassName BusinessUtils
 * @Author Hai.Ming
 * @Date 2021/5/30 18:48
 * @Description 业务工具类
 */
public class BusinessUtils {

    /**
     * @MethodName filePathReplace
     * @Author Hai.Ming
     * @Date 2021/5/30 18:51
     * @Description 文件路径中的分隔符替换成 File.separator
     **/
    public static String filePathReplace(String filePath) {
        if (StringUtils.contains(filePath, CommonConstants.SEPARATOR_FORWARD_SLASH)) {
            return StringUtils.replace(filePath, CommonConstants.SEPARATOR_FORWARD_SLASH, File.separator);
        }
        if (StringUtils.contains(filePath, CommonConstants.SEPARATOR_REVERSE_SLASH)) {
            return StringUtils.replace(filePath, CommonConstants.SEPARATOR_REVERSE_SLASH, File.separator);
        }
        return filePath;
    }
}

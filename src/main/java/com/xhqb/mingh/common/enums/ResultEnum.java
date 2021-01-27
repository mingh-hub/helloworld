package com.xhqb.mingh.common.enums;

/**
 * 响应信息
 */
public enum ResultEnum {
    /**
     * 业务处理成功
     */
    SUCCESS("000000","成功"),
    /**
     * 业务处理失败
     */
    FAILURE("999999","业务异常, 请稍后再试"),
    /**
     * 参数缺失
     */
    PARAMS_IS_MISSING("000001","参数缺失, 请稍后再试"),
    /**
     * IO 操作异常
     */
    IO_EXCEPTION("000002", "IO 操作异常"),
    /**
     * 文件获取失败
     */
    FILE_NOT_FOUND("000003", "文件不存在"),
    /**
     * 文件目录不存在
     */
    FILE_PATH_NOT_FOUND("000003", "文件目录不存在"),
    ;
     private String code;
     private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

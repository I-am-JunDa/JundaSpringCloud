package com.junda.pojo;

import lombok.Getter;

/**
 * @author libai
 */
public interface ComConstant {
    /**
     * 成功标识
     */
    Integer SYS_OK_200 = 200;
    /**
     * 系统异常
     */
    Integer SYS_SERVER_ERROR_500 = 500;

    /**
     * 异常错误的编码的定义
     */
    @Getter
    public static enum ErrorEnum{
        FAIL_501("前段数据校验异常", 501),
        FAIL_502("feign调用异常", 502),
        FAIL_503("数据库数据为空", 503);
        private String desc;
        private int value;
        ErrorEnum(String desc, int value) {
            this.desc = desc;
            this.value = value;
        }
    }


}

package com.junda.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author libai
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7678504843288601965L;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回编码 200成功
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 成功标识
     */
    private Boolean success = true;
    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public static Result<Object> ok() {
        Result<Object> r = new Result();
        r.setSuccess(true);
        r.setCode(ComConstant.SYS_OK_200);
        r.setMsg("操作成功");
        return r;
    }

    public static Result<Object> ok(String msg) {
        Result<Object> r = new Result();
        r.setSuccess(true);
        r.setCode(ComConstant.SYS_OK_200);
        r.setMsg(msg);
        return r;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result();
        r.setData(data);
        r.setSuccess(true);
        r.setCode(ComConstant.SYS_OK_200);
        r.setMsg("操作成功");
        return r;
    }


    public static Object error(Integer code,String msg) {
        Result<Object> r = new Result();
        r.setSuccess(true);
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}

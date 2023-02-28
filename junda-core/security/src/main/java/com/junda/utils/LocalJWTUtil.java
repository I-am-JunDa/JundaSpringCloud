package com.junda.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * jwt 工具类二次封装
 */
public class LocalJWTUtil {

    private static String USERID = "USERID";

    private static String DEFAULT = "DEFAULT";

    /**
     * 基于默认的jwt生成tocken
     */
    public static String createToken(String str) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(DEFAULT, str);
        return JWTUtil.createToken(map, str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 基于默认的jwt生成tocken,
     * secondNumber tocken有效期
     */
    public static String createToken(String str, int secondNumber) {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put(DEFAULT, str);
        DateTime now = DateTime.now();
        DateTime deadTime = now.offsetNew(DateField.SECOND, secondNumber);
        payload.put(JWTPayload.ISSUED_AT, now); //签发时间
        payload.put(JWTPayload.NOT_BEFORE, now); //生效时间
        payload.put(JWTPayload.EXPIRES_AT, deadTime); //过期时间
        return JWTUtil.createToken(payload, DEFAULT.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * 解析token并验证
     */
    public static JWT parseToken(String token) {
        JWT jwt = JWT.of(token);
        boolean validate = jwt.setKey(DEFAULT.getBytes(StandardCharsets.UTF_8)).validate(0);
        if (!validate) {
            throw new RuntimeException("token已经过期");
        }
        return JWT.of(token);
    }

    /**
     * 返回payload中的参数
     */
    public static String getPayload(JWT jwt, String name) {
        if (StrUtil.isEmpty(name)) {
            return jwt.getPayload(DEFAULT).toString();
        } else {
            return jwt.getPayload(name).toString();
        }
    }


}

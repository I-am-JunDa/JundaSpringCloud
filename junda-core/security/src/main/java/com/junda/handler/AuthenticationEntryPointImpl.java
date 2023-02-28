package com.junda.handler;

import cn.hutool.http.HttpStatus;
import com.junda.pojo.Result;
import com.junda.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //把英文字符转化为中文
        String transToChinese = transToChinese(authException.getMessage());
        //处理异常,未认证
        String jsonString = JSON.toJSONString(Result.error(HttpStatus.HTTP_UNAUTHORIZED,transToChinese));
        WebUtils.renderString(response, jsonString);
    }


    /**
     * 把字符转换为中文
     * @param english
     * @return
     */
    private static String transToChinese(String english){
        if(english.equals("Full authentication is required to access this resource")){
            return "需要完全身份验证才能访问此资源";
        }
        return english;
    }

}

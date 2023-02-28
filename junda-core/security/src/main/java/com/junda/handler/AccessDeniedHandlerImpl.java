package com.junda.handler;

import cn.hutool.http.HttpStatus;
import com.junda.pojo.Result;
import com.junda.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当访问接口没有权限时，自定义的返回结果
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //处理异常
        String jsonString = JSON.toJSONString(Result.error(HttpStatus.HTTP_FORBIDDEN,"权限不足"));
        WebUtils.renderString(response, jsonString);
    }
}

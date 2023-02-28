package com.junda.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('user:nihao')")
    public String productAmount() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String servletPath = request.getServletPath(); //获得url路径
        String contextPath = request.getContextPath();
        return "nihao";
    }

}

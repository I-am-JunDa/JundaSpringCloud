package com.junda.auth.controller;


import com.junda.pojo.RBAC.User;
import com.junda.pojo.Result;
import com.junda.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/auth")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }

    /**
     * 这里是带token进入的，后端才可以判断是哪一个用户
     * @return
     */
    @PostMapping("/logout")
    public Result logout(){
        //登录，退出登录
        return loginService.logout();
    }

}

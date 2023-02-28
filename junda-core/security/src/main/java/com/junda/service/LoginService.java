package com.junda.service;


import com.junda.pojo.RBAC.User;
import com.junda.pojo.Result;

public interface LoginService {
    Result login(User user);

    Result logout();
}

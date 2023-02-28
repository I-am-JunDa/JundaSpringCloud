package com.junda.config;


import com.junda.pojo.security.LoginUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户权限资源池，直接存内存中，如果有redis的话，可以直接用redis
 * 如果是微服务的话 就必须要用redis了，因为不同的服务在不同服务器上，内存资源不能共享
 */
@Component
@AllArgsConstructor
public class AuthUserPool {

    public final Map<String, LoginUser> userMap = new HashMap<>();

    public void addAuthUser(LoginUser loginUser) {
        this.userMap.put(loginUser.getUser().getId(), loginUser);
    }

    public LoginUser getAuthUser(String userId) {
        return this.userMap.get(userId);
    }

    public void deleteAuthUser(String userId) {
        userMap.remove(userId);
    }


}

package com.junda.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.junda.config.AuthUserPool;
import com.junda.pojo.RBAC.User;
import com.junda.pojo.Result;
import com.junda.pojo.security.LoginUser;
import com.junda.service.LoginService;
import com.junda.utils.LocalJWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * token 的有效期 ，单位 /秒
     */
    @Value("${jwt.usefulTime}")
    private Integer usefulTime;

//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private AuthUserPool authUserPool;

    /**
     *  方法过程流 UserDetailsServiceImpl[loadUserByUsername] ==> LoginServiceImpl[login]
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //转到loadUserByUsername去校验
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (ObjUtil.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userid生成一个 jwt存入Result进行返回
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        //创建token ,有效期 120秒
        String token = LocalJWTUtil.createToken(loginUser.getUser().getId(),usefulTime);
        //把完整的用户信息存入redis，userid作为key
        //redisTemplate.opsForValue().set("login:"+loginUser.getUser().getId(),loginUser);
        authUserPool.addAuthUser(loginUser);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    @Override
    public Result logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId();
        //删除redis里面存的用户信息
        //redisTemplate.delete("login:"+userid);
        authUserPool.deleteAuthUser(userid);
        return new Result().ok("退出成功");
    }

}

package com.junda.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.junda.mapper.MenuMapper;
import com.junda.mapper.UserMapper;
import com.junda.pojo.RBAC.User;
import com.junda.pojo.security.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户账号校验，查询用户信息
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired(required = true)
    private MenuMapper menuMapper;

    @Autowired(required = true)
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息-这里只需要用户名就可以了，密码的话，他系统会自动的去判断
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, username));
        if(ObjUtil.isNull(user)){
            throw new RuntimeException("用户名错误，或者用户不存在");
        }
        //查询对应的权限信息,把权限信息 存到user里面
        List<String> perms = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user,perms);
    }
}

package com.junda.filter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import com.junda.config.AuthUserPool;
import com.junda.pojo.security.LoginUser;
import com.junda.utils.LocalJWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt 认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private AuthUserPool authUserPool;

    /**
     * 调用接口的时候会先经过这个过虑器
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            //没有token放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userID = "";
        try {
            JWT jwt = LocalJWTUtil.parseToken(token);
            userID = LocalJWTUtil.getPayload(jwt,null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage()); //TODO 这里应该做个全局异常捕获
        }
        //从authUserPool获取用户信息【仅在单机单服务下有效】
        LoginUser authUser = authUserPool.getAuthUser(userID);
        //从redis获取用户信息
        //Object authUser = redisTemplate.opsForValue().get("login:" + userID);
        if (ObjUtil.isNull(authUser)){
            throw new RuntimeException("用户未登录");
        }
        LoginUser loginUser = BeanUtil.copyProperties(authUser, LoginUser.class);

        //TODO 获取权限信息封装到 authorities，取出权限信息 loginUser.getAuthorities()
        //吧loginUser存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}

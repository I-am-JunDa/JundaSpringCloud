package com.junda.pojo.security;


import cn.hutool.core.collection.CollUtil;
import com.junda.pojo.RBAC.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    private List<String> permissions;

    @JsonIgnore
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(User user,List<String> permissions){
        this.user = user;
        this.permissions = permissions;
    }

    //获取权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(CollUtil.isNotEmpty(authorities)){
            return authorities;
        }
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

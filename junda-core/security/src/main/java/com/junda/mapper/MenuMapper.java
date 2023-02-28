package com.junda.mapper;

import com.junda.pojo.RBAC.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(String userId);
}

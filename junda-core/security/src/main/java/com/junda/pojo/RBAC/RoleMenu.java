package com.junda.pojo.RBAC;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_role_menu")
public class RoleMenu {
    @TableId(type = IdType.AUTO)
    private String id;

    private String roleId;

    private String menuId;
}

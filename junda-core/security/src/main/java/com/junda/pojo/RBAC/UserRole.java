package com.junda.pojo.RBAC;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class UserRole implements Serializable {

    @TableId(type = IdType.AUTO)
    private String id;

    private String userId;

    private String roleId;
}

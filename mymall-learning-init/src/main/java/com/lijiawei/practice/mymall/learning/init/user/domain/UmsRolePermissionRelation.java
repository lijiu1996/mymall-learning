package com.lijiawei.practice.mymall.learning.init.user.domain;

import lombok.Data;

/**
    * 后台用户角色和权限关系表
    */
@Data
public class UmsRolePermissionRelation {
    private Long id;

    private Long roleId;

    private Long permissionId;
}
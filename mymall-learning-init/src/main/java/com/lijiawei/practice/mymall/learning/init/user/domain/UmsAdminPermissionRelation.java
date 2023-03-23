package com.lijiawei.practice.mymall.learning.init.user.domain;

import lombok.Data;

/**
    * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
    */
@Data
public class UmsAdminPermissionRelation {
    private Long id;

    private Long adminId;

    private Long permissionId;

    private Integer type;
}
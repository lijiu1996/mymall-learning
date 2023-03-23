package com.lijiawei.practice.mymall.learning.init.user.domain;

import lombok.Data;

/**
    * 后台用户和角色关系表
    */
@Data
public class UmsAdminRoleRelation {
    private Long id;

    private Long adminId;

    private Long roleId;
}
package com.lijiawei.practice.mymall.learning.init.user.bean.dto;

import lombok.Data;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Data
public class UmsAdminLoginParam {
//    @NotEmpty(message = "用户名不能为空")
    private String username;
//    @NotEmpty(message = "密码不能为空")
    private String password;
}

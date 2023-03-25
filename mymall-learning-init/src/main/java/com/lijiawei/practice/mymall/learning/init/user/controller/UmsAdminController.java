package com.lijiawei.practice.mymall.learning.init.user.controller;

import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import com.lijiawei.practice.mymall.learning.init.common.util.JWTUtil;
import com.lijiawei.practice.mymall.learning.init.user.bean.dto.UmsAdminLoginParam;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdmin;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsPermission;
import com.lijiawei.practice.mymall.learning.init.user.service.UmsAdminService;
import com.lijiawei.practice.mymall.learning.init.user.service.UmsCustomService;
import org.checkerframework.common.util.report.qual.ReportUnqualified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsCustomService umsCustomService;

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private JWTUtil jwtUtil;

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result) {
//        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
//        if (umsAdmin == null) {
//            CommonResult.failed();
//        }
//        return CommonResult.success(umsAdmin);
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = umsCustomService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", jwtUtil.getTokenHead());
        return CommonResult.success(tokenMap);
    }

    // 退出当前用户登录
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public CommonResult logout() {
        umsCustomService.logout();
        return CommonResult.success("账号注销成功");
    }

    // 查询某一用户所拥有的全部权限
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }

    @PreAuthorize("hasAuthority('ums:permission:fail')")
    @RequestMapping(value = "/permission/testFailed", method = RequestMethod.GET)
    public CommonResult testPermissionFailed() {
        return CommonResult.success("通过了权限认证逻辑, 程序内部bug, 请定位");
    }
}

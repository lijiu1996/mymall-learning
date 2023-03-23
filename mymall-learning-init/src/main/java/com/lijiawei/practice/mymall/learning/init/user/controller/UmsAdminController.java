package com.lijiawei.practice.mymall.learning.init.user.controller;

import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import com.lijiawei.practice.mymall.learning.init.user.bean.dto.UmsAdminLoginParam;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdmin;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsPermission;
import com.lijiawei.practice.mymall.learning.init.user.service.UmsAdminService;
import com.lijiawei.practice.mymall.learning.init.user.service.UmsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private String tokenHeader = "Authorization";

    private String tokenHead = "Bearer";

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
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

//    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
//    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
//        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
//        return CommonResult.success(permissionList);
//    }
}

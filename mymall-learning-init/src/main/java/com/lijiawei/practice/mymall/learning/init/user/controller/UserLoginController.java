package com.lijiawei.practice.mymall.learning.init.user.controller;

import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import com.lijiawei.practice.mymall.learning.init.user.service.UmsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Li JiaWei
 * @ClassName: UserLoginController
 * @Description:
 * @Date: 2023/3/21 9:57
 * @Version: 1.0
 */
// 实现用户注册登录功能
@RestController
@RequestMapping("/sso")
public class UserLoginController {

    @Autowired
    private UmsCustomService umsCustomService;

    // 获取验证码
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return umsCustomService.generateAuthCode(telephone);
    }

    // 判断验证码是否正确
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return umsCustomService.verifyAuthCode(telephone,authCode);
    }


}

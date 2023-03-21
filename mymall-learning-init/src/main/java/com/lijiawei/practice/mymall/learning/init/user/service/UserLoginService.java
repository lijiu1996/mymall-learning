package com.lijiawei.practice.mymall.learning.init.user.service;

import cn.hutool.core.util.RandomUtil;
import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import com.lijiawei.practice.mymall.learning.init.common.constant.RedisConstant;
import com.lijiawei.practice.mymall.learning.init.common.service.impl.CommonRedisService;
import com.lijiawei.practice.mymall.learning.init.common.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Li JiaWei
 * @ClassName: UserLoginService
 * @Description:
 * @Date: 2023/3/21 10:00
 * @Version: 1.0
 */
@Service
public class UserLoginService {

    @Autowired
    private CommonRedisService commonRedisService;

    // 根据手机号生成验证码
    public CommonResult generateAuthCode(String telephone) {
        // 1. 正则校验手机号
        if (RegexUtil.isPhoneInvalid(telephone)) {
            return CommonResult.failed("手机号格式错误");
        }
        // 2. 生成验证码并保存到redis
        String code = RandomUtil.randomNumbers(6);
        String key = String.format(RedisConstant.REDIS_PREFIX_AUTH_CODE,telephone);
        commonRedisService.set(key,code,RedisConstant.REDIS_EXPIRE_AUTH_CODE);
        return CommonResult.success(code,"成功生成验证码");
    }

    // 校验验证码是否正确
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (RegexUtil.isPhoneInvalid(telephone) || RegexUtil.isCodeInvalid(authCode)) {
            return CommonResult.failed("手机号或者验证码格式错误");
        }
        String code = commonRedisService.get(String.format(RedisConstant.REDIS_PREFIX_AUTH_CODE,telephone));
        boolean equals = authCode.equals(code);
        if (equals) {
            return CommonResult.success(true,"验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }

    // 根据手机号和验证码实现注册登录功能
    // 注册登录放在一起 如果手机号没有查到 那么就在数据库中搜索对应记录
    public void login() {
        // 1. 校验

        // 2. 查找手机号对应验证码是否正确

        // 3. 查找对应用户是否存在

        // 4. 执行登录流程 将用户信息保存
    }
}

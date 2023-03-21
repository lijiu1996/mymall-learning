package com.lijiawei.practice.mymall.learning.init.user.service;

import cn.hutool.core.util.RandomUtil;
import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import com.lijiawei.practice.mymall.learning.init.common.constant.RedisConstant;
import com.lijiawei.practice.mymall.learning.init.common.service.impl.CommonRedisService;
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
        String authcode = RandomUtil.randomNumbers(6);
        String key = String.format(RedisConstant.REDIS_PREFIX_AUTH_CODE,telephone);
        commonRedisService.set(key,authcode,RedisConstant.REDIS_EXPIRE_AUTH_CODE);
        return CommonResult.success(true,"成功生成验证码");
    }

    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        // 1. 根据手机号从redis中取, 然后比较 返回相等或者不相等
        String code = commonRedisService.get(String.format(RedisConstant.REDIS_PREFIX_AUTH_CODE,telephone));
        boolean equals = authCode.equals(code);
        if (equals) {
            return CommonResult.success(true,"验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }
}

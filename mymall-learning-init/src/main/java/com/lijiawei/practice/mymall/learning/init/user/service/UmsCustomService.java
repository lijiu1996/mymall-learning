package com.lijiawei.practice.mymall.learning.init.user.service;

import cn.hutool.core.util.RandomUtil;
import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import com.lijiawei.practice.mymall.learning.init.common.constant.RedisConstant;
import com.lijiawei.practice.mymall.learning.init.common.service.impl.CommonRedisService;
import com.lijiawei.practice.mymall.learning.init.common.util.JSONUtil;
import com.lijiawei.practice.mymall.learning.init.common.util.JWTUtil;
import com.lijiawei.practice.mymall.learning.init.common.util.RegexUtil;
import com.lijiawei.practice.mymall.learning.init.user.bean.dto.AdminUserDetails;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author Li JiaWei
 * @ClassName: UserLoginService
 * @Description:
 * @Date: 2023/3/21 10:00
 * @Version: 1.0
 */
@Service
public class UmsCustomService {

    @Autowired
    private CommonRedisService commonRedisService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    // 根据手机号生成验证码
    public CommonResult generateAuthCode(String telephone) {
        // 1. 正则校验手机号
        if (RegexUtil.isPhoneInvalid(telephone)) {
            return CommonResult.failed("手机号格式错误");
        }
        // 2. 生成验证码并保存到redis
        String code = RandomUtil.randomNumbers(6);
        String key = String.format(RedisConstant.REDIS_PREFIX_USER_AUTHCODE,telephone);
        commonRedisService.set(key,code,RedisConstant.REDIS_EXPIRE_USER_AUTHCODE);
        return CommonResult.success(code,"成功生成验证码");
    }

    // 校验验证码是否正确
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (RegexUtil.isPhoneInvalid(telephone) || RegexUtil.isCodeInvalid(authCode)) {
            return CommonResult.failed("手机号或者验证码格式错误");
        }
        String code = commonRedisService.get(String.format(RedisConstant.REDIS_PREFIX_USER_AUTHCODE,telephone));
        boolean equals = authCode.equals(code);
        if (equals) {
            return CommonResult.success(true,"验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }

    // 根据手机号和验证码实现注册登录功能
    // 注册登录放在一起 如果手机号没有查到 那么就在数据库中搜索对应记录
    /**
     *  SpringSecurity 实践三 编写登录逻辑 代替原本的UsernamePasswordAuthenticationFilter完成自定义校验逻辑
     *      实现校验成功向前端返回jwtToken功能
     */
    public String login(String username, String password) {
        // 1. 校验
        // 2. 查找手机号对应验证码是否正确
        // 3. 校验用户名密码
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(token);
        // 4. 校验通过后执行登录流程
        // springSecurity 校验成功后会把数据封装进principal
        // 将登录用户信息保存到redis中 并且生成jwtToken返回给前端
        // 后续前端发来的token中携带有userId --> 解析token得到userId 然后查询redis 拿到用户信息
        AdminUserDetails userInfo = (AdminUserDetails) authenticate.getPrincipal();
        Long id = userInfo.getUmsAdmin().getId();
        String key = String.format(RedisConstant.REDIS_PREFIX_USER_ID, id);
        commonRedisService.set(key,userInfo,RedisConstant.REDIS_EXPIRE_USER_ID);
        String jwtToken = jwtUtil.createJWTByUserId(id);

        return jwtToken;
    }

    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userInfo = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userInfo.getUmsAdmin().getId();
        String key = String.format(RedisConstant.REDIS_PREFIX_USER_ID,userId);
        commonRedisService.del(key);
    }
}

package com.lijiawei.practice.mymall.learning.init.common.config.component;

import com.lijiawei.practice.mymall.learning.init.common.constant.RedisConstant;
import com.lijiawei.practice.mymall.learning.init.common.service.impl.CommonRedisService;
import com.lijiawei.practice.mymall.learning.init.common.util.JWTUtil;
import com.lijiawei.practice.mymall.learning.init.user.bean.dto.AdminUserDetails;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * JWT登录授权过滤器
 * Created by macro on 2018/4/26.
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CommonRedisService commonRedisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(jwtUtil.getTokenHeader());
        if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(jwtUtil.getTokenHead())) {
            String authToken = authHeader.substring(jwtUtil.getTokenHead().length() + 1);// The part after "Bearer "
            Long userId = jwtUtil.getUserIdFromJWTToken(authToken);
            log.info("checking userId:{}", userId);
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                String key = String.format(RedisConstant.REDIS_PREFIX_USER_ID,userId);
                AdminUserDetails userInfo = commonRedisService.get(key);
                if (userInfo != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userInfo.getUmsAdmin(), null, userInfo.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

    public static void main(String[] args) {
        String s = "Bearer 123456";
        String substring = s.substring("Bearer".length());
        System.out.println(substring);
        System.out.println(s.substring(0));
        System.out.println(s.substring(2));
        System.out.println(s.substring(4));
        System.out.println(s.substring(6));
    }
}

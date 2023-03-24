package com.lijiawei.practice.mymall.learning.init.user.config.component;

import com.lijiawei.practice.mymall.learning.init.common.util.JSONUtil;
import com.lijiawei.practice.mymall.learning.init.common.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * JWT登录授权过滤器
 * Created by macro on 2018/4/26.
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(jwtUtil.getTokenHeader());
        if (authHeader != null && authHeader.startsWith(jwtUtil.getTokenHead())) {
            String authToken = authHeader.substring(jwtUtil.getTokenHead().length());// The part after "Bearer "
            Long userId = null;
            try {
                String jwtInfo = jwtUtil.parseJWTInfo(authToken);
                if (jwtInfo != null) {
                    HashMap<String,Long> map = JSONUtil.string2Obj(jwtInfo, HashMap.class);
                    if (map != null)
                        userId = map.get("id");
                }
            } catch (Exception e) {
                throw new RuntimeException("JWT解析失败",e);
            }
            log.info("checking userId:{}", userId);
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}

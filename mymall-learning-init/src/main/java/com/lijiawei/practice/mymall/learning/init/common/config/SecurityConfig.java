package com.lijiawei.practice.mymall.learning.init.common.config;

import com.lijiawei.practice.mymall.learning.init.user.bean.dto.AdminUserDetails;
import com.lijiawei.practice.mymall.learning.init.common.config.component.JwtAuthenticationTokenFilter;
import com.lijiawei.practice.mymall.learning.init.common.config.component.RestAuthenticationEntryPoint;
import com.lijiawei.practice.mymall.learning.init.common.config.component.RestfulAccessDeniedHandler;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdmin;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsPermission;
import com.lijiawei.practice.mymall.learning.init.user.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;


/**
 * SpringSecurity的配置
 * Created by macro on 2018/4/26.
 *      Security 实践七 查看当前容器中注册的全部securityFilters
 *         难点: 由于SpringBoot版本较旧 没能直接找到Filter
 *         查询步骤 1. Arrays.stream(run.getBeanDefinitionNames()).filter(s -> s.toLowerCase().contains("security")).collect(Collectors.toList())
 *                2. 获取其中的springSecurityFilterChain
 *                3. 发现该版本实际上是一个叫FilterChainProxy的类
 *                4. 从该类中可以获取到全部的Filter
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable() // 关闭csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不使用session保存
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                ).permitAll()
                .antMatchers("/admin/login", "/admin/register").anonymous() // 仅允许匿名访问
                .antMatchers(HttpMethod.OPTIONS).permitAll() // 放行跨域请求的OPTION
//                .antMatchers("/**").permitAll()  //测试时放开注释保证全部接口能够访问
                .anyRequest().authenticated();  // 其余所有请求全部需要鉴权认证
//         禁用缓存
        httpSecurity.headers().cacheControl();
//        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        // security 开启跨域
        httpSecurity.cors();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     *  SpringSecurity 实践二
     *      替换掉默认的passwordEncoder
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * SpringSecurity 实践一
     *      替换掉默认UserDetailsService, 用自定义的数据库查询代替
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            UmsAdmin admin = adminService.getAdminByUsername(username);
            if (admin != null) {
                List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin,permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    /**
     * SpringSecurity 实践四 增加TokenFilter过滤器进行token的解析并且保存UserInfo
     *  保证非登录接口在携带token后处理过程中可以拥有用户信息
     * @return
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

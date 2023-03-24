package com.lijiawei.practice.mymall.learning.init.common.config.component;

import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import com.lijiawei.practice.mymall.learning.init.common.util.JSONUtil;
import com.lijiawei.practice.mymall.learning.init.common.util.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringSecurity 实践五 自定义认证异常处理接口
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        CommonResult<String> commonResult = CommonResult.unauthorized(authException.getMessage());
        WebUtil.renderJsonString(response,JSONUtil.obj2String(commonResult));
    }
}

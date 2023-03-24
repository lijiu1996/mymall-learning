package com.lijiawei.practice.mymall.learning.init.common.config.component;

import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import com.lijiawei.practice.mymall.learning.init.common.util.JSONUtil;
import com.lijiawei.practice.mymall.learning.init.common.util.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringSecurity 实践六 自定义授权异常处理接口
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        CommonResult<String> commonResult = CommonResult.forbidden(e.getMessage());
        WebUtil.renderJsonString(response, JSONUtil.obj2String(commonResult));
    }
}

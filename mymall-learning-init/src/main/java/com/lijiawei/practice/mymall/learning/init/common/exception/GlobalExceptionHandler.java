package com.lijiawei.practice.mymall.learning.init.common.exception;

import cn.hutool.core.util.StrUtil;
import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import com.lijiawei.practice.mymall.learning.init.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Method;

/**
 * @author Li JiaWei
 * @ClassName: GlobalExceptionHandler
 * @Description:
 * @Date: 2023/3/24 15:23
 * @Version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult validExceptionHandler(MethodArgumentNotValidException exception) {
        Method method = exception.getParameter().getMethod();
        FieldError error = exception.getFieldError();
        String errorMsg = StrUtil.format("方法'{}' 参数'{}'校验失败: '{}'{},实际传入{}",method.getName(),error.getObjectName(), error.getField(), error.getDefaultMessage(), error.getRejectedValue());
        log.error(errorMsg);
        return CommonResult.failed(ResultCode.VALIDATE_FAILED,errorMsg);
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult handle(Exception e)
    {
        if (e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;
            log.error("[业务异常]{}",e.getMessage(),e);
            return CommonResult.failed(be.getCode(),be.getMsg());
        } else {
            log.error("[系统异常]{}",e.getMessage(),e);
            return CommonResult.failed();
        }
    }
}

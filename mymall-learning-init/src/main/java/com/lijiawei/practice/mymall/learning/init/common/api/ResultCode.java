package com.lijiawei.practice.mymall.learning.init.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode implements IReturnCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "运行时异常"),
    VALIDATE_FAILED(405, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

}

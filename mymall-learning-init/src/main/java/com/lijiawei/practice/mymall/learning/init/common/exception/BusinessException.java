package com.lijiawei.practice.mymall.learning.init.common.exception;

import com.lijiawei.practice.mymall.learning.init.common.api.IReturnCode;
import lombok.Data;

/**
 * @author Li JiaWei
 * @ClassName: BusinessException
 * @Description:    自定义业务异常
 * @Date: 2023/3/24 16:28
 * @Version: 1.0
 */
@Data
public class BusinessException extends RuntimeException {

    private int code;

    private String msg;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}

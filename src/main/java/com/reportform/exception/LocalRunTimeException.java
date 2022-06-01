package com.reportform.exception;

import com.reportform.enums.ErrorEnum;

/**
 * @Author xun
 * @create 2022/5/27 19:23
 */
public class LocalRunTimeException extends RuntimeException {
    private ErrorEnum errorEnum;

    //默认错误
    public LocalRunTimeException(String message) {
        super(message);
    }

    public LocalRunTimeException(ErrorEnum errorEnum) {
        super(errorEnum.getErrMsg());
        this.errorEnum = errorEnum;
    }
}

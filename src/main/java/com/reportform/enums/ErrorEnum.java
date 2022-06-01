package com.reportform.enums;

/**
 * @Author xun
 * @create 2022/5/27 19:24
 */
public class ErrorEnum {
    /**
     * 错误状态码
     */
    private Integer errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    ErrorEnum(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}

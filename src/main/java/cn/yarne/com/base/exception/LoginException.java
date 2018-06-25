package cn.yarne.com.base.exception;

import cn.yarne.com.base.other.HttpCode;

public class LoginException extends BaseException {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Exception e) {
        super(message, e);
    }

    protected HttpCode getCode() {
        return HttpCode.LOGIN_FAIL;
    }
}

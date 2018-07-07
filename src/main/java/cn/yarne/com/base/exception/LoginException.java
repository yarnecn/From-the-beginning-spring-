package cn.yarne.com.base.exception;

import cn.yarne.com.base.other.HttpCode;


/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
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

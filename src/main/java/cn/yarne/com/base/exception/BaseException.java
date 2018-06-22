package cn.yarne.com.base.exception;

import cn.yarne.com.base.other.HttpCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;

public abstract class BaseException extends RuntimeException  {
    public BaseException() {
    }
    public BaseException(Throwable ex) {
        super(ex);
    }
    public BaseException(String message) {
        super(message);
    }
    public BaseException(String message, Throwable ex) {
        super(message, ex);
    }
    public void handler(ModelMap modelMap) {
        modelMap.put("code", this.getCode().value());
        if (StringUtils.isNotBlank(this.getMessage())) {
            modelMap.put("msg", this.getMessage());
        } else {
            modelMap.put("msg", this.getCode().msg());
        }

        modelMap.put("timestamp", System.currentTimeMillis());
    }

    protected abstract HttpCode getCode();
}

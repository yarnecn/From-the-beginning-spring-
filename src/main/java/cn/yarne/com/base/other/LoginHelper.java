package cn.yarne.com.base.other;

import cn.yarne.com.base.exception.LoginException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.taglibs.standard.resources.Resources;

public final class LoginHelper {
    private LoginHelper() {
    }

    public static final Boolean login(String account, String password, String host) {
        UsernamePasswordToken token = new UsernamePasswordToken(account, password, host);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            return subject.isAuthenticated();
        } catch (LockedAccountException var6) {
            throw new LoginException("登录失败");
        } catch (DisabledAccountException var7) {
            throw new LoginException("登录失败");
        } catch (ExpiredCredentialsException var8) {
            throw new LoginException("登录失败");
        } catch (Exception var9) {
            throw new LoginException("登录失败");
        }
    }
}

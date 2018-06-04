package cn.yarne.com.base.shiro.filter;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ShiroFormFilter extends FormAuthenticationFilter {

    private static final Logger logger = Logger.getLogger(ShiroFormFilter.class);

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        logger.debug("用户登录成功之后的处理");
        //清除原先的地址 shiro权限框架在认证时认证通过之后 就会跳转到上一次访问的路径
        WebUtils.getAndClearSavedRequest(request);
        //之前有个bug，若上一次请求为注销请求 则会无限死循环 认证成功会立即发送注销请求
        WebUtils.redirectToSavedRequest(request, response, "/index.jsp");
        return false;
    }
}

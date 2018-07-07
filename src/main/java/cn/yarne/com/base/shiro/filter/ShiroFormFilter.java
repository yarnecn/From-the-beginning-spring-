package cn.yarne.com.base.shiro.filter;

import cn.yarne.com.base.model.SysUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 * shiro认证成功之后的拦截处理
 */
public class ShiroFormFilter extends FormAuthenticationFilter {

    private static final Logger logger = Logger.getLogger(ShiroFormFilter.class);

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        logger.debug("用户登录成功之后的处理");
        //获取已登录的用户信息
        SysUser activeUser = (SysUser) subject.getPrincipal();
        //获取session
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        //把用户信息保存到session
        session.setAttribute("sysUser", activeUser);
        //shiro权限框架在认证时认证通过之后 就会跳转到上一次访问的路径
        //所以我们调用这个方法清除之前保存的请求地址
        WebUtils.getAndClearSavedRequest(request);
        //之前有个bug，若上一次请求为注销请求 则会无限死循环 认证成功会立即发送注销请求
        WebUtils.redirectToSavedRequest(request, response, "/index");
        return false;
    }
}

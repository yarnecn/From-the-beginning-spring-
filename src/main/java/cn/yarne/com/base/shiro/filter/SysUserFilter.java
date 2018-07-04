package cn.yarne.com.base.shiro.filter;

import cn.yarne.com.base.other.Constants;
import cn.yarne.com.base.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by 14641 on 2018/7/4.
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, sysUserService.getUserByUsername(username));
        return true;
    }

}

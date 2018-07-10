package cn.yarne.com.base.shiro.filter;

import com.alibaba.druid.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 14641 on 2018/7/9.
 */
public class ValidateCodeFilter extends AccessControlFilter {

    private static final Logger logger = Logger.getLogger(ValidateCodeFilter.class);

    private boolean jcaptchaEbabled = true;//是否开启验证码支持
    private String jcaptchaParam = "verCode";//前台提交的验证码参数名
    private String failureKeyAttribute = "shiroLoginFailure"; //验证失败后存储到的属性名

    public void setJcaptchaEbabled(boolean jcaptchaEbabled) {
        this.jcaptchaEbabled = jcaptchaEbabled;
    }
    public void setJcaptchaParam(String jcaptchaParam) {
        this.jcaptchaParam = jcaptchaParam;
    }
    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        //1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        servletRequest.setAttribute("jcaptchaEbabled", jcaptchaEbabled);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
       //2、判断验证码是否禁用 或不是表单提交（允许访问）
        if (jcaptchaEbabled == false
                || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }
       //3、此时是表单提交，验证验证码是否正确
        Session session = SecurityUtils.getSubject().getSession();
        String code = (String) session.getAttribute("code");
        if(code!=null){
            /*
            * 因为现在前台传过来的验证码不能直接获取到，所以我们换一种方法获取
            * WebUtils是shiro提供的一个工具类
            */
            HttpServletRequest httpRequest = WebUtils.toHttp(servletRequest);
            String currentCode = httpRequest.getParameter(jcaptchaParam);
            // 调用alibaba连接池提供的StringUtils工具类，忽略大小写比较
            return StringUtils.equalsIgnoreCase(code, currentCode);
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.debug("验证码错误");
        //如果验证码失败了，存储失败 key 属性
        servletRequest.setAttribute(failureKeyAttribute, "CodeError");
        return true;
    }
}

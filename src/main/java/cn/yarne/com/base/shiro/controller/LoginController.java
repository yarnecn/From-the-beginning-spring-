package cn.yarne.com.base.shiro.controller;


import cn.yarne.com.base.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
@Controller
@RequestMapping("/")
@Api(value = "登录接口", description = "登录模块的接口")
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "login",method = { RequestMethod.GET, RequestMethod.POST })
    @ApiOperation(value = "登录接口")
    public String login( Model model, HttpServletRequest request) {
        //如果登录失败从request中获取认证异常信息，shrioLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        //根据shrio返回的异常路径判断，抛出指定异常信
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名不存在";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "密码错误";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
            error = "用户已经锁定";
        } else if(exceptionClassName != null) {
           error = "其他错误：" + exceptionClassName;
        }
        //防止用户登录成功之后回退到登录页面继续登录
        if(error==null){
            if(SecurityUtils.getSubject().isAuthenticated()){
                return "index";
            }
        }else{
            logger.debug(error);
            model.addAttribute("error", error);
        }
        return "system/login";
    }

    /**
     * 配置了shiro的退出之后，这个一般不会用到了
     * @return
     */
    @RequestMapping(value = "logout", method = { RequestMethod.GET, RequestMethod.POST })
    @ApiOperation(value = "登录失败接口")
    public String logout(){
        //获取认证主体
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //当前用户注销
            currentUser.logout();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "system/login";
    }

}

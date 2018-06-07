package cn.yarne.com.base.controller;


import cn.yarne.com.base.model.SysUser;
import cn.yarne.com.base.model.Users;
import cn.yarne.com.base.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/system")
@Api(value = "登录接口", description = "登录模块的接口")
public class SystemController {

    private static final Logger logger = Logger.getLogger(HelloController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口")
    public String login(@ApiParam(required = true, value = "用户的信息(登录名，密码)") SysUser sysUser, Model model) {
        // 获取shiro主体
        Subject subject = SecurityUtils.getSubject();
        // 根据传来的登录名密码创建一个token用户密码令牌
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getLoginName(), sysUser.getPassword());
        String msg = null;
        try {
            //由我们的自定义主体来验证token信息,会跳转到ream中验证
            subject.login(token);
            //获取用户登录的session信息
            Session session = subject.getSession();

            logger.debug("session的Id" + session.getId());
            logger.debug("session主机地址" + session.getHost());
            logger.debug("session有效期间" + session.getId());
            return "illusory";
        }
        //根据抛出的异常判断错误信息
        catch (IncorrectCredentialsException e) {
            msg = "密码错误" + token.getPrincipal() + " was incorrect.";
            model.addAttribute("message", msg);
            logger.debug(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            model.addAttribute("message", msg);
            logger.debug(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定The account for username " + token.getPrincipal() + " was locked.";
            model.addAttribute("message", msg);
            logger.debug(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用The account for username " + token.getPrincipal() + " was disabled.";
            model.addAttribute("message", msg);
            logger.debug(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已被禁用the account for username " + token.getPrincipal() + "  was expired.";
            model.addAttribute("message", msg);
            logger.debug(msg);
        } catch (UnknownAccountException e) {
            msg = "账户不存在!There is no user with username of " + token.getPrincipal();
            model.addAttribute("message", msg);
            logger.debug(msg);
        } catch (UnauthorizedException e) {
            msg = "ddd" + e.getMessage();
            model.addAttribute("message", msg);
            logger.debug(msg);
        }

        return "login";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
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
        return "login";
    }

}

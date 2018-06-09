package cn.yarne.com.base.controller;


import cn.yarne.com.base.model.SysUser;
import cn.yarne.com.base.model.Users;
import cn.yarne.com.base.other.ResultData;
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
    @ResponseBody
    public String login(@ApiParam(required = true, value = "用户的信息(登录名，密码)") SysUser sysUser, Model model) {
        //创建一个返回的结果集
        ResultData resultData=new ResultData();
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
            resultData.setCode(0);
            resultData.setMsg("登录成功");
        }catch (Exception e) {
            e.printStackTrace();
            msg = "用户名或者密码错误" + token.getPrincipal() + " was incorrect.";
            logger.debug(msg);
            resultData.setCode(1);
            resultData.setMsg(msg);
        }
        return msg;
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

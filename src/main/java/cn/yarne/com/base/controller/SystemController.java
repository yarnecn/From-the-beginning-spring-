package cn.yarne.com.base.controller;


import cn.yarne.com.base.exception.LoginException;
import cn.yarne.com.base.model.SysUser;
import cn.yarne.com.base.other.Constants;
import cn.yarne.com.base.other.LoginHelper;
import cn.yarne.com.base.other.ResultData;
import cn.yarne.com.base.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Assert;
import org.apache.taglibs.standard.resources.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public ResultData login(@ApiParam(required = true, value = "用户的信息(登录名，密码)") SysUser sysUser, Model model, HttpServletRequest request) {
        //创建一个返回的结果集
        ResultData resultData=new ResultData();
        // 获取shiro主体
        Subject subject = SecurityUtils.getSubject();
        // 根据传来的登录名密码创建一个token用户密码令牌
        Assert.notNull(sysUser.getLoginName(), "LoginName");
        Assert.notNull(sysUser.getPassword(), "Password");
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getLoginName(), sysUser.getPassword());
        String msg = null;
        String clientIp = (String) request.getSession().getAttribute(Constants.USER_IP);
        if (LoginHelper.login(sysUser.getLoginName(), sysUser.getPassword(), clientIp)) {
            request.setAttribute("msg", "[" + sysUser.getLoginName() + "]登录成功.");
            resultData.setCode(0);
            resultData.setMsg("登录成功");
            return resultData;
        }
        request.setAttribute("msg", "[" +sysUser.getLoginName() + "]登录失败.");
        throw new LoginException("登录失败");
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
        return "system/login";
    }

}

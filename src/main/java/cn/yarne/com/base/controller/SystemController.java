package cn.yarne.com.base.controller;


import cn.yarne.com.base.model.SysUser;
import cn.yarne.com.base.model.Users;
import cn.yarne.com.base.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/system")
@Api(value = "登录接口", description = "登录模块的接口")
public class SystemController {

    private static final Logger logger=Logger.getLogger(HelloController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ApiOperation(value = "登录接口")
    public String login(@ApiParam(required = true, value = "用户的信息(登录名，密码)")SysUser sysUser, HttpServletRequest request){
        // 获取shiro主体
        Subject subject = SecurityUtils.getSubject();
        // 根据传来的登录名密码创建一个token令牌
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(sysUser.getLoginName(),sysUser.getPassword());
        try {
            //由我们的自定义主体来验证token信息,会跳转到ream中验证
            subject.login(usernamePasswordToken);
        } catch (Exception exception){
            String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");

        }


        return "login";
    }




}

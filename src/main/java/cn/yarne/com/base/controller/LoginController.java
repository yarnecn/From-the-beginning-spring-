package cn.yarne.com.base.controller;


import cn.yarne.com.base.service.SysUserService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@Api(value = "登录接口", description = "登录模块的接口")
public class LoginController {

    private static final Logger logger=Logger.getLogger(HelloController.class);

    @Autowired
    private SysUserService sysUserService;




}

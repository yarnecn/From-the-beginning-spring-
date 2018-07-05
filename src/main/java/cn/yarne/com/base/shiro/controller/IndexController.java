package cn.yarne.com.base.shiro.controller;

import cn.yarne.com.base.model.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 14641 on 2018/6/9.
 */
@RequestMapping("/")
@Controller
public class IndexController {
    @RequestMapping(value = "index")
    public String index(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        model.addAttribute("sysUser",sysUser);
        //默认跳转到用户登录页面
        return "index";
    }
}

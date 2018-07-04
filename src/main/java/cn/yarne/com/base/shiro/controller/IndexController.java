package cn.yarne.com.base.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 14641 on 2018/6/9.
 */
@RequestMapping("/")
@Controller
public class IndexController {
    @RequestMapping(value = "index")
    public String index(){
        //默认跳转到用户登录页面
        return "index";
    }
}

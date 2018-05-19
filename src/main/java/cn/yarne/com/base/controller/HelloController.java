package cn.yarne.com.base.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yarne.com.base.model.Users;
import cn.yarne.com.base.service.HelloService;

@Controller
@RequestMapping("/")
public class HelloController {
	
	private static final  Logger logger=Logger.getLogger(HelloController.class);
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping(value="hello",produces="application/json;charset=utf-8")
	@ResponseBody
	public String hello(){
		logger.info("这是日志的打印方式");
		logger.info(helloService.helloDemo());
		Users user=new Users();
		user.setAge(16);
		user.setName("张三");
		helloService.insertUsers(user);
		return helloService.helloDemo();
	}
}

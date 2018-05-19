package cn.yarne.com.base.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yarne.com.base.service.DemoService;

@RestController
@RequestMapping("/")
public class HelloController {
	
	private static final  Logger logger=Logger.getLogger(HelloController.class);
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping("hello")
	public String hello(){
		logger.info("这是日志的打印方式");
		return demoService.helloDemo();
	}
}

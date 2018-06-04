package cn.yarne.com.base.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yarne.com.base.model.Users;
import cn.yarne.com.base.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/")
@Api(value = "测试接口", description = "主要用来测试")
public class HelloController {
	
	private static final  Logger logger=Logger.getLogger(HelloController.class);
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping(value="hello",produces="application/json;charset=utf-8",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "这个接口主要是搭建框架的时候测试以及以后的规范")
	public String hello(@ApiParam(required = false, value = "用做输出的一句话")String hello){
		logger.info("这是日志的打印方式"+hello);
		logger.info(helloService.helloDemo());
		Users user=new Users();
		user.setAge(16);
		user.setName("张三");
		helloService.insertUsers(user);
		return helloService.helloDemo();
	}

}

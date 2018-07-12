package cn.yarne.com.base.controller;


import cn.yarne.com.base.model.Users;
import cn.yarne.com.base.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
@Controller
@RequestMapping("/")
@Api(value = "测试接口", description = "主要用来测试")
public class HelloController {
	//getLogger里面写的哪个类，之后日志就会打在哪个类下边
	private static final  Logger logger=Logger.getLogger(HelloController.class);
	
	@Autowired
	private HelloService helloService;

	@Autowired
	private RedisTemplate redisTemplate;
	
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


	@RequestMapping(value="helloRedis",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "redis测试")
	public String helloRedis(@ApiParam(required = false, value = "redis key")String name){
		ValueOperations valueOperations = redisTemplate.opsForValue();
		valueOperations.set(name,"这是redis");
		String s = (String) valueOperations.get(name);
		return s;
	}

}

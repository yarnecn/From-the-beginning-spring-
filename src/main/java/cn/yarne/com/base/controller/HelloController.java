package cn.yarne.com.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yarne.com.base.service.DemoService;

@RestController
@RequestMapping("/")
public class HelloController {
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping("hello")
	public String hello(){
		return demoService.helloDemo();
	}
}

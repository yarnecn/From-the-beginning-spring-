package cn.yarne.com.base.service.impl;

import org.springframework.stereotype.Service;

import cn.yarne.com.base.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Override
	public String helloDemo() {
		return "Hello";
	}

}

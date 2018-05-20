package cn.yarne.com.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.yarne.com.base.mapper.HelloMapper;
import cn.yarne.com.base.model.Users;
import cn.yarne.com.base.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	@SuppressWarnings("unused")
	@Autowired
	private HelloMapper helloMapper;
	
	@Override
	public String helloDemo() {
		Users selectUsers = helloMapper.selectUsers();
		return selectUsers.getName();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED) 
	public Integer insertUsers(Users user) {
		Integer insertUsers = helloMapper.insertUsers(user);
		int i=0/0;
		return insertUsers;
	}
	
	
	

}

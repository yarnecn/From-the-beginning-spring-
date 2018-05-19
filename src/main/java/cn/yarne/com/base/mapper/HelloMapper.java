package cn.yarne.com.base.mapper;

import cn.yarne.com.base.model.Users;

public interface HelloMapper {

	Users selectUsers();
	
	Integer insertUsers(Users user);
}

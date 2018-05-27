package cn.yarne.com.base.mapper;

import cn.yarne.com.base.example.SysUserExample;
import cn.yarne.com.base.model.Users;

public interface HelloMapper extends BaseMapper<Users,SysUserExample>  {

	Users selectUsers();
	
	Integer insertUsers(Users user);
}

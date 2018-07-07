package cn.yarne.com.base.mapper;

import cn.yarne.com.base.example.SysUserExample;
import cn.yarne.com.base.model.Users;
/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
public interface HelloMapper extends BaseMapper<Users,SysUserExample>  {

	Users selectUsers();
	
	Integer insertUsers(Users user);
}

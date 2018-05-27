package cn.yarne.com.base.shiro.realm;


import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.yarne.com.base.controller.HelloController;

public class UsersRealm extends AuthorizingRealm  {
	
	//定义Log对象
	@SuppressWarnings("unused")
	private static final  Logger logger=Logger.getLogger(HelloController.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		return null;
	}

}

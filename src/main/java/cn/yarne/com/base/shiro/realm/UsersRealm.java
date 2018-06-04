package cn.yarne.com.base.shiro.realm;


import cn.yarne.com.base.model.SysUser;
import cn.yarne.com.base.service.SysUserService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.yarne.com.base.controller.HelloController;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersRealm extends AuthorizingRealm {

    //定义Log对象
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(HelloController.class);

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("开始执行授权");
        //获取当前的用户
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.debug("开始执行认证");

        //从token中获取用户名
        String username = (String) token.getPrincipal();

        //去数据库中查询用户名是否存在
        SysUser sysUser = sysUserService.getUserByUsername(username);

        //如果不存在则抛出用户不存在异常
        if (sysUser == null) {
            throw new UnknownAccountException();
        }

        //设置盐，也可以不设置。盐通常为登录用户名+时间戳，添加到数据库当中。
        String salt = "FaQ";

        //一个简单的认证器，如果不设置盐的话就不能添加第三个参数；
        // 第一个参数可以放查询出来的对象、也可以放获取的登录用户名，第二个参数为查询出来的密码，第三个参数为盐，第四个参数为上面设置的setName
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(),
                ByteSource.Util.bytes(salt), this.getName());
        return simpleAuthenticationInfo;
    }

}

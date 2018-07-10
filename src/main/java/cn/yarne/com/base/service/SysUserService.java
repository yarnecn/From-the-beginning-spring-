package cn.yarne.com.base.service;

import cn.yarne.com.base.model.SysUser;

import java.util.List;


/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
public interface SysUserService {

    /**
     * 根据登录名得到用户
     * @param username
     * @return
     */
    SysUser getUserByUsername(String username);

    /**
     * 条件查询用户
     * @param username
     * @param loginName
     * @return
     */
    List<SysUser> querySysUsersByExample(String username,String loginName);
}

package cn.yarne.com.base.service;

import cn.yarne.com.base.model.SysUser;


/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
public interface SysUserService {

    SysUser getUserByUsername(String username);

}

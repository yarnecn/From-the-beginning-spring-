package cn.yarne.com.base.service.impl;

import cn.yarne.com.base.example.SysUserExample;
import cn.yarne.com.base.mapper.SysUserMapper;
import cn.yarne.com.base.model.SysUser;
import cn.yarne.com.base.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUsername(String username) {
        SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andLoginNameEqualTo(username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if(sysUsers.size()>0){
            return sysUsers.get(0);
        }
        return null;
    }
}

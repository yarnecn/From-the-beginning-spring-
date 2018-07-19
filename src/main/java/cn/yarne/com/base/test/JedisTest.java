package cn.yarne.com.base.test;

import cn.yarne.com.base.model.SysUser;
import cn.yarne.com.base.util.RedisListUtil;

/**
 * Created by 14641 on 2018/7/18.
 */
public class JedisTest {

    public static void main(String []rogs){

        RedisListUtil redisListUtil=new RedisListUtil();
        RedisListUtil.Lists lists = redisListUtil.new Lists();
        for (int a=0;a<300;a++){
            SysUser sysUser=new SysUser();
            sysUser.setUsername("userName"+a);
            sysUser.setAge(a);
            sysUser.setLoginName("loginName"+a);
            sysUser.setEmail("email"+a);
            sysUser.setPassword("password"+a);
            sysUser.setId(a);
            long list02 = lists.lpush(sysUser.getId().toString(), sysUser.toString());
        }
    }

}

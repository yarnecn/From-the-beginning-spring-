package cn.yarne.com.base.test;

import cn.yarne.com.base.model.SysUser;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.List;

/**
 * Created by 14641 on 2018/7/5.
 * ehcache测试类，简单的操作
 */
public class EhcacheTest {
    public static void main(String []orgs){

        //获取缓存管理器
        CacheManager cacheManager=CacheManager.create("./src/main/resources/ehcache/ehcache.xml");
        String[] cacheNames = cacheManager.getCacheNames();
        for (String cach:cacheNames){
            System.out.println(cach);
        }
        Cache helloWorldCache = cacheManager.getCache("HelloWorldCache");
        List keys = helloWorldCache.getKeys();
        for(Object key:keys){
            System.out.println(key);
        }

        Element element=new Element("key1","value1");
        helloWorldCache.put(element);

        Element element1 = helloWorldCache.get("key1");
        System.out.println(element1);
        System.out.println(element1.getObjectValue());

        helloWorldCache.remove("key1");

        SysUser sysUser=new SysUser();
        sysUser.setAge(20);
        sysUser.setUsername("这是名字");
        helloWorldCache.put(new Element("user1",sysUser));
        System.out.println(helloWorldCache.get("user1").getObjectValue());
        //刷新缓存
        helloWorldCache.flush();
        //关闭缓存管理器
        cacheManager.shutdown();
    }

}

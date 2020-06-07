package cn.yarne.com.base.autoconfig;

/**
 * Created by 14641 on 2020/6/6.
 */
public class TestTarget {

    private String name;

    public TestTarget() {
        this("自动配置");
    }

    public TestTarget(String name) {
        this.name = name;
    }

    public void hello(){
        System.out.println("hello: "+name);
    }

}

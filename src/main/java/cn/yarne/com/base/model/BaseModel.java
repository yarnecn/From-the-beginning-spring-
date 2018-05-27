package cn.yarne.com.base.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

public interface BaseModel extends Serializable {
	BaseModel.IFAPBeanReflection beanEntity();

    String[] attributeNames();

    Object getAttribute(String var1);

    void setAttribute(String var1, Object var2);

    public interface IFAPBeanReflection {
        Map<String, Method> getSetMethods();

        void setSetMethods(Map<String, Method> var1);

        Map<String, Method> getGetMethods();

        void setGetMethods(Map<String, Method> var1);
    }
}

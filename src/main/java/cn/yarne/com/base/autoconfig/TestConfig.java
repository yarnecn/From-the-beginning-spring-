package cn.yarne.com.base.autoconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * Created by 14641 on 2020/6/6.
 */
@Configuration
public class TestConfig implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        boolean testTarget = ClassUtils.isPresent(TestTarget.class.getName(), configurableListableBeanFactory.getBeanClassLoader());
        if(!testTarget){
            System.out.println("类名不存在");
            return;
        }
        boolean testTarget1 = configurableListableBeanFactory.containsBean("TestTarget");
        if(testTarget1){
            System.out.println("Bean已经注入");
            return;
        }
        registoryBean(configurableListableBeanFactory);

    }


    public void registoryBean(ConfigurableListableBeanFactory beanFactory){
        if(beanFactory instanceof BeanDefinitionRegistry){
            GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
            genericBeanDefinition.setBeanClass(TestTarget.class);
            ((BeanDefinitionRegistry)beanFactory).registerBeanDefinition("TestTarget",genericBeanDefinition);
        }else{
            beanFactory.registerSingleton("TestTarget",new TestTarget());
        }
    }
}

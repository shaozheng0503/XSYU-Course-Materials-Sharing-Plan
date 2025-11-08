package cn.bugstack.springframework.aop.framework.autoproxy;

import cn.bugstack.springframework.aop.*;
import cn.bugstack.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import cn.bugstack.springframework.aop.framework.ProxyFactory;
import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.PropertyValues;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.BeanFactoryAware;
import cn.bugstack.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.bugstack.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * BeanPostProcessor implementation that creates AOP proxies based on all candidate
 * Advisors in the current BeanFactory. This class is completely generic; it contains
 * no special code to handle any particular aspects, such as pooling aspects.
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该类是一个 Bean 后置处理器，用于根据当前 BeanFactory 中的所有候选 Advisor 为 Bean 创建 AOP 代理。
 * 它是通用的，不包含处理特定切面（如池化切面）的特殊代码。
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    // 持有 DefaultListableBeanFactory 引用，用于获取 Bean 和 Advisor 等信息
    private DefaultListableBeanFactory beanFactory;

    // 用于存储早期创建的代理对象的引用，避免重复代理
    private final Set<Object> earlyProxyReferences = Collections.synchronizedSet(new HashSet<Object>());

    /**
     * 实现 BeanFactoryAware 接口的方法，用于设置 BeanFactory。
     *
     * @param beanFactory 要设置的 BeanFactory
     * @throws BeansException 如果设置过程中出现异常
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // 将传入的 BeanFactory 转换为 DefaultListableBeanFactory 并赋值给成员变量
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    /**
     * 在 Bean 实例化之前执行的后置处理方法。
     *
     * @param beanClass Bean 的类
     * @param beanName Bean 的名称
     * @return 返回 null 表示不进行特殊处理，继续正常的实例化流程
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    /**
     * 在 Bean 实例化之后执行的后置处理方法。
     *
     * @param bean 实例化后的 Bean 对象
     * @param beanName Bean 的名称
     * @return 返回 true 表示继续进行属性填充等后续操作
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    /**
     * 判断给定的 Bean 类是否为基础设施类（如 Advice、Pointcut、Advisor）。
     *
     * @param beanClass 要判断的 Bean 类
     * @return 如果是基础设施类返回 true，否则返回 false
     */
    private boolean isInfrastructureClass(Class<?> beanClass) {
        // 判断 Bean 类是否是 Advice、Pointcut 或 Advisor 的子类或实现类
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    /**
     * 在 Bean 初始化之前执行的后置处理方法。
     *
     * @param bean 要处理的 Bean 对象
     * @param beanName Bean 的名称
     * @return 直接返回传入的 Bean 对象，不进行特殊处理
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在 Bean 初始化之后执行的后置处理方法。
     *
     * @param bean 初始化后的 Bean 对象
     * @param beanName Bean 的名称
     * @return 如果该 Bean 不在早期代理引用集合中，则尝试为其创建代理；否则直接返回该 Bean
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 检查该 Bean 是否不在早期代理引用集合中
        if (!earlyProxyReferences.contains(beanName)) {
            // 如果不在，调用 wrapIfNecessary 方法尝试为其创建代理
            return wrapIfNecessary(bean, beanName);
        }

        // 如果在早期代理引用集合中，直接返回该 Bean
        return bean;
    }

    /**
     * 如果有必要，为 Bean 创建代理对象。
     *
     * @param bean 要处理的 Bean 对象
     * @param beanName Bean 的名称
     * @return 如果需要代理则返回代理对象，否则返回原 Bean 对象
     */
    protected Object wrapIfNecessary(Object bean, String beanName) {
        // 检查该 Bean 是否为基础设施类，如果是则直接返回该 Bean
        if (isInfrastructureClass(bean.getClass())) return bean;

        // 从 BeanFactory 中获取所有 AspectJExpressionPointcutAdvisor 类型的 Bean
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        // 遍历所有的 Advisor
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            // 获取 Advisor 的 ClassFilter
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 过滤匹配类，如果该 Bean 不匹配 ClassFilter，则跳过当前 Advisor
            if (!classFilter.matches(bean.getClass())) continue;

            // 创建 AdvisedSupport 对象，用于配置代理相关信息
            AdvisedSupport advisedSupport = new AdvisedSupport();

            // 创建 TargetSource 对象，封装目标 Bean
            TargetSource targetSource = new TargetSource(bean);
            // 设置目标源
            advisedSupport.setTargetSource(targetSource);
            // 设置方法拦截器
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            // 设置方法匹配器
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            // 设置是否使用 CGLIB 代理
            advisedSupport.setProxyTargetClass(true);

            // 使用 ProxyFactory 创建代理对象并返回
            return new ProxyFactory(advisedSupport).getProxy();
        }

        // 如果没有匹配的 Advisor，直接返回原 Bean 对象
        return bean;
    }

    /**
     * 获取早期 Bean 引用，用于解决循环依赖问题。
     *
     * @param bean Bean 对象
     * @param beanName Bean 的名称
     * @return 如果需要代理则返回代理对象，否则返回原 Bean 对象
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        // 将该 Bean 名称添加到早期代理引用集合中
        earlyProxyReferences.add(beanName);
        // 调用 wrapIfNecessary 方法尝试为其创建代理
        return wrapIfNecessary(bean, beanName);
    }

    /**
     * 在 Bean 属性填充之前对属性值进行后置处理。
     *
     * @param pvs 要处理的属性值
     * @param bean Bean 对象
     * @param beanName Bean 的名称
     * @return 直接返回传入的属性值，不进行特殊处理
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

}
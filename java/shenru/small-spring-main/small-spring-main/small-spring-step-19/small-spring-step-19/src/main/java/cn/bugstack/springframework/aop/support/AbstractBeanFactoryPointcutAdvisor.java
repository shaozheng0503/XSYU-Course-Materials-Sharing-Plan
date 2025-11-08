package cn.bugstack.springframework.aop.support;

import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.BeanFactoryAware;
import cn.hutool.core.lang.Assert;
import org.aopalliance.aop.Advice;

/**
 * @author zhangdd on 2022/2/27
 *
 * 该抽象类 AbstractBeanFactoryPointcutAdvisor 继承自 AbstractPointcutAdvisor 并实现了 BeanFactoryAware 接口。
 * 它主要用于管理与 BeanFactory 相关的切点通知（Advice），通过 BeanFactory 从容器中获取 Advice 实例。
 */
public abstract class AbstractBeanFactoryPointcutAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    // 存储 Advice 对应的 Bean 名称，通过该名称从 BeanFactory 中获取 Advice 实例
    private String adviceBeanName;

    // 持有 BeanFactory 的引用，用于从容器中获取 Advice 对应的 Bean 实例
    private BeanFactory beanFactory;

    // 用于存储 Advice 实例，使用 transient volatile 修饰。
    // transient 表示该字段不参与序列化，volatile 保证多线程环境下该字段的可见性
    private transient volatile Advice advice;

    // 用于同步访问 advice 字段的监视器对象
    private transient volatile Object adviceMonitor = new Object();

    /**
     * 设置 Advice 对应的 Bean 名称
     *
     * @param adviceBeanName Advice 对应的 Bean 名称
     */
    public void setAdviceBeanName(String adviceBeanName) {
        this.adviceBeanName = adviceBeanName;
    }

    /**
     * 获取 Advice 对应的 Bean 名称
     *
     * @return Advice 对应的 Bean 名称
     */
    public String getAdviceBeanName() {
        return adviceBeanName;
    }

    /**
     * 设置 Advice 实例，使用同步块确保线程安全
     *
     * @param advice 要设置的 Advice 实例
     */
    public void setAdvice(Advice advice) {
        synchronized (this.adviceMonitor) {
            this.advice = advice;
        }
    }

    /**
     * 获取 Advice 实例。如果 Advice 实例已经存在则直接返回，
     * 否则从 BeanFactory 中根据 adviceBeanName 获取 Advice 实例，并进行缓存
     *
     * @return Advice 实例
     */
    @Override
    public Advice getAdvice() {
        // 先尝试从本地缓存中获取 Advice 实例
        Advice advice = this.advice;
        if (null != advice) {
            return advice;
        }

        // 检查 adviceBeanName 和 beanFactory 是否已经设置
        Assert.state(this.adviceBeanName != null, "'adviceBeanName' must be specified");
        Assert.state(this.beanFactory != null, "BeanFactory must be set to resolve 'adviceBeanName'");

        // 从 BeanFactory 中获取 Advice 实例
        advice = this.beanFactory.getBean(this.adviceBeanName, Advice.class);
        // 将获取到的 Advice 实例进行缓存
        this.advice = advice;

        return advice;
    }

    /**
     * 实现 BeanFactoryAware 接口的方法，设置 BeanFactory 并重置 adviceMonitor
     *
     * @param beanFactory 要设置的 BeanFactory
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        // 重置 adviceMonitor
        resetAdviceMonitor();
    }

    /**
     * 重置 adviceMonitor 对象，当前实现是创建一个新的 Object 实例
     * 注释部分原本计划根据 BeanFactory 的类型获取单例对象，但代码未完成
     */
    private void resetAdviceMonitor() {
//        if (this.beanFactory instanceof ConfigurableBeanFactory){
//            this.adviceMonitor= ((ConfigurableBeanFactory) this.beanFactory).getSingleton()
//        }
        this.adviceMonitor = new Object();
    }
}
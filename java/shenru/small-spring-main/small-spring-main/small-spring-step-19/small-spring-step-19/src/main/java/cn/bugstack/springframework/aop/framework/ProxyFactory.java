package cn.bugstack.springframework.aop.framework;

import cn.bugstack.springframework.aop.AdvisedSupport;

/**
 * Factory for AOP proxies for programmatic use, rather than via a bean
 * factory. This class provides a simple way of obtaining and configuring
 * AOP proxies in code.
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该类是一个用于以编程方式创建 AOP 代理的工厂类，而不是通过 Bean 工厂来创建。
 * 它提供了一种在代码中简单获取和配置 AOP 代理的方式。
 */
public class ProxyFactory {

    // 持有 AdvisedSupport 对象的引用，该对象包含了 AOP 代理的配置信息，
    // 如目标对象、拦截器、方法匹配器等，以及是否使用 CGLIB 代理的标志。
    private AdvisedSupport advisedSupport;

    /**
     * 构造函数，用于初始化 ProxyFactory 对象。
     *
     * @param advisedSupport 包含 AOP 代理配置信息的 AdvisedSupport 对象
     */
    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    /**
     * 获取代理对象的方法。
     * 该方法会调用 createAopProxy 方法创建 AopProxy 对象，
     * 然后调用 AopProxy 对象的 getProxy 方法获取最终的代理对象。
     *
     * @return 创建好的代理对象
     */
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    /**
     * 创建 AopProxy 对象的私有方法。
     * 根据 AdvisedSupport 对象中的 isProxyTargetClass 方法的返回值，
     * 决定使用 CGLIB 代理还是 JDK 动态代理。
     *
     * @return 创建好的 AopProxy 对象
     */
    private AopProxy createAopProxy() {
        // 检查是否配置为使用 CGLIB 代理
        if (advisedSupport.isProxyTargetClass()) {
            // 如果是，则创建 Cglib2AopProxy 对象
            return new Cglib2AopProxy(advisedSupport);
        }

        // 如果不是，则创建 JdkDynamicAopProxy 对象
        return new JdkDynamicAopProxy(advisedSupport);
    }

}
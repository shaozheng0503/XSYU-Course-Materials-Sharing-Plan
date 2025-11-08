package cn.bugstack.springframework.aop.framework;

/**
 * Delegate interface for a configured AOP proxy, allowing for the creation
 * of actual proxy objects.
 *
 * <p>Out-of-the-box implementations are available for JDK dynamic proxies
 * and for CGLIB proxies, as applied by DefaultAopProxyFactory
 *
 * AOP 代理的抽象
 *
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 此接口是配置好的 AOP 代理的委托接口，其主要作用是允许创建实际的代理对象。
 * 通过实现该接口，可以根据不同的代理方式（如 JDK 动态代理、CGLIB 代理）创建出对应的代理对象，
 * 从而为目标对象添加额外的增强逻辑，实现 AOP（面向切面编程）的功能。
 *
 * 在默认情况下，DefaultAopProxyFactory 类会应用 JDK 动态代理和 CGLIB 代理的实现。
 */
public interface AopProxy {

    /**
     * 获取代理对象的方法。
     * 实现该接口的类需要实现此方法，根据具体的代理策略创建并返回代理对象。
     *
     * @return 创建好的代理对象，类型为 Object，具体使用时可能需要进行强制类型转换
     */
    Object getProxy();

}
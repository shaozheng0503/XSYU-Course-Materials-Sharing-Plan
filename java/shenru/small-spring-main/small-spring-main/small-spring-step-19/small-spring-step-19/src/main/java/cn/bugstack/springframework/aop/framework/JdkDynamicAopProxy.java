package cn.bugstack.springframework.aop.framework;

import cn.bugstack.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK-based {@link AopProxy} implementation for the Spring AOP framework,
 * based on JDK {@link java.lang.reflect.Proxy dynamic proxies}.
 * <p>
 * JDK 动态代理
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该类是 Spring AOP 框架中基于 JDK 动态代理实现的 AopProxy 接口类。
 * 它利用 Java 的反射机制，通过 java.lang.reflect.Proxy 类来创建代理对象，
 * 从而为目标对象添加额外的增强逻辑，实现 AOP（面向切面编程）的功能。
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    // 持有 AdvisedSupport 对象的引用，该对象包含了 AOP 代理的配置信息，
    // 如目标对象、拦截器、方法匹配器等。
    private final AdvisedSupport advised;

    /**
     * 构造函数，用于初始化 JdkDynamicAopProxy 对象。
     *
     * @param advised 包含 AOP 代理配置信息的 AdvisedSupport 对象
     */
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 实现 AopProxy 接口的方法，用于获取代理对象。
     *
     * @return 创建好的代理对象
     */
    @Override
    public Object getProxy() {
        // 使用 Proxy.newProxyInstance 方法创建代理对象。
        // 第一个参数是类加载器，这里使用当前线程的上下文类加载器。
        // 第二个参数是目标对象实现的接口数组，代理对象将实现这些接口。
        // 第三个参数是 InvocationHandler 对象，这里传入当前对象（this），
        // 当代理对象的方法被调用时，会触发 InvocationHandler 的 invoke 方法。
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advised.getTargetSource().getTargetClass(), this);
    }

    /**
     * 实现 InvocationHandler 接口的方法，当代理对象的方法被调用时会触发此方法。
     *
     * @param proxy 代理对象
     * @param method 被调用的方法
     * @param args 方法调用时传入的参数
     * @return 方法调用的返回值
     * @throws Throwable 可能抛出的异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 检查当前方法是否匹配 AOP 配置中的方法匹配器
        if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
            // 如果匹配，获取 AOP 配置中的方法拦截器
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            // 创建 ReflectiveMethodInvocation 对象，封装方法调用的相关信息
            // 并将其传递给方法拦截器的 invoke 方法，执行增强逻辑
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
        }
        // 如果不匹配，直接调用目标对象的方法
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }

}
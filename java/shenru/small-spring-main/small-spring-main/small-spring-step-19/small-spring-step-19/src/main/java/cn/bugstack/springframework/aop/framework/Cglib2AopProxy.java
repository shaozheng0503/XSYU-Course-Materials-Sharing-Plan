package cn.bugstack.springframework.aop.framework;

import cn.bugstack.springframework.aop.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB2-based {@link AopProxy} implementation for the Spring AOP framework.
 *
 * <p><i>Requires CGLIB 2.1+ on the classpath.</i>.
 * As of Spring 2.0, earlier CGLIB versions are not supported anymore.
 *
 * <p>Objects of this type should be obtained through proxy factories,
 * configured by an AdvisedSupport object. This class is internal
 * to Spring's AOP framework and need not be used directly by client code.
 *
 * 这是 Spring AOP 框架中基于 CGLIB2 的 AopProxy 实现类。
 * 它利用 CGLIB 库来创建代理对象，实现 AOP 的功能。
 * 需要在类路径中包含 CGLIB 2.1 及以上版本，从 Spring 2.0 开始，不再支持早期的 CGLIB 版本。
 * 此类的对象应该通过代理工厂获取，并由 AdvisedSupport 对象进行配置。
 * 它是 Spring AOP 框架的内部类，客户端代码通常不需要直接使用。
 */
public class Cglib2AopProxy implements AopProxy {

    // 持有 AdvisedSupport 对象的引用，该对象包含了 AOP 代理的配置信息，如目标对象、拦截器、方法匹配器等
    private final AdvisedSupport advised;

    /**
     * 构造函数，用于初始化 Cglib2AopProxy 对象。
     *
     * @param advised 包含 AOP 代理配置信息的 AdvisedSupport 对象
     */
    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 实现 AopProxy 接口的方法，用于获取代理对象。
     *
     * @return 创建好的代理对象
     */
    @Override
    public Object getProxy() {
        // 创建 CGLIB 的 Enhancer 对象，用于生成代理类
        Enhancer enhancer = new Enhancer();
        // 设置代理类的父类为目标对象的类，这样代理类会继承目标对象的所有方法
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        // 设置代理类要实现的接口，这里使用目标对象实现的接口
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        // 设置方法拦截器，当代理对象的方法被调用时，会触发该拦截器的逻辑
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        // 创建代理对象并返回
        return enhancer.create();
    }

    /**
     * 内部静态类，实现了 CGLIB 的 MethodInterceptor 接口，用于拦截代理对象的方法调用。
     */
    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        // 持有 AdvisedSupport 对象的引用，用于获取 AOP 代理的配置信息
        private final AdvisedSupport advised;

        /**
         * 构造函数，用于初始化 DynamicAdvisedInterceptor 对象。
         *
         * @param advised 包含 AOP 代理配置信息的 AdvisedSupport 对象
         */
        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        /**
         * 实现 MethodInterceptor 接口的方法，当代理对象的方法被调用时会触发此方法。
         *
         * @param o 代理对象
         * @param method 被调用的方法
         * @param objects 方法调用时传入的参数
         * @param methodProxy CGLIB 的方法代理对象，用于调用目标对象的方法
         * @return 方法调用的返回值
         * @throws Throwable 可能抛出的异常
         */
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            // 创建 CglibMethodInvocation 对象，用于封装方法调用的相关信息
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
            // 检查当前方法是否匹配 AOP 配置中的方法匹配器
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
                // 如果匹配，则调用 AOP 配置中的方法拦截器的 invoke 方法，执行增强逻辑
                return advised.getMethodInterceptor().invoke(methodInvocation);
            }
            // 如果不匹配，直接调用目标对象的方法
            return methodInvocation.proceed();
        }
    }

    /**
     * 内部静态类，继承自 ReflectiveMethodInvocation，用于封装 CGLIB 方法调用的相关信息。
     */
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        // CGLIB 的方法代理对象，用于调用目标对象的方法
        private final MethodProxy methodProxy;

        /**
         * 构造函数，用于初始化 CglibMethodInvocation 对象。
         *
         * @param target 目标对象
         * @param method 被调用的方法
         * @param arguments 方法调用时传入的参数
         * @param methodProxy CGLIB 的方法代理对象
         */
        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        /**
         * 重写父类的 proceed 方法，用于调用目标对象的方法。
         *
         * @return 方法调用的返回值
         * @throws Throwable 可能抛出的异常
         */
        @Override
        public Object proceed() throws Throwable {
            // 使用 CGLIB 的方法代理对象调用目标对象的方法
            return this.methodProxy.invoke(this.target, this.arguments);
        }

    }

}
package cn.bugstack.springframework.aop.framework.adapter;

import cn.bugstack.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Interceptor to wrap am {@link cn.bugstack.springframework.aop.MethodBeforeAdvice}.
 * Used internally by the AOP framework; application developers should not need
 * to use this class directly.
 *
 * 该类是一个拦截器，用于包装 MethodBeforeAdvice 接口的实现类。
 * 它是 AOP 框架内部使用的，应用开发者通常不需要直接使用这个类。
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    // 持有 MethodBeforeAdvice 接口的引用，用于存储具体的前置通知逻辑
    private MethodBeforeAdvice advice;

    /**
     * 无参构造函数，用于创建一个空的 MethodBeforeAdviceInterceptor 实例。
     * 之后可以通过 setAdvice 方法设置具体的前置通知。
     */
    public MethodBeforeAdviceInterceptor() {
    }

    /**
     * 带参构造函数，用于创建一个带有具体前置通知的 MethodBeforeAdviceInterceptor 实例。
     *
     * @param advice 具体的 MethodBeforeAdvice 实现类对象，包含前置通知的逻辑
     */
    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    /**
     * 实现 MethodInterceptor 接口的核心方法，用于在目标方法执行前调用前置通知，并继续执行目标方法。
     *
     * @param methodInvocation 封装了目标方法调用的上下文信息，包括目标方法、参数和目标对象等
     * @return 目标方法的返回值
     * @throws Throwable 可能抛出的异常
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 在目标方法执行之前，调用 MethodBeforeAdvice 的 before 方法，执行前置通知逻辑
        // 传入目标方法、方法参数和目标对象
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        // 调用 methodInvocation.proceed() 继续执行目标方法，并返回目标方法的返回值
        return methodInvocation.proceed();
    }

    /**
     * 获取当前拦截器中存储的前置通知对象。
     *
     * @return MethodBeforeAdvice 类型的前置通知对象
     */
    public MethodBeforeAdvice getAdvice() {
        return advice;
    }

    /**
     * 设置当前拦截器要使用的前置通知对象。
     *
     * @param advice 要设置的 MethodBeforeAdvice 实现类对象
     */
    public void setAdvice(MethodBeforeAdvice advice) {
        this.advice = advice;
    }
}
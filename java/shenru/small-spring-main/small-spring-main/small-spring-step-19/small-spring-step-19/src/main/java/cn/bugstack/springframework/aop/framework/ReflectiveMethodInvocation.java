package cn.bugstack.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * <p>Invokes the target object using reflection. Subclasses can override the
 * #invokeJoinpoint() method to change this behavior, so this is also
 * a useful base class for more specialized MethodInvocation implementations.
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该类使用反射机制调用目标对象的方法。
 * 子类可以重写 #invokeJoinpoint() 方法（此处虽未定义，但有扩展可能）来改变调用行为，
 * 因此它是更专业的 MethodInvocation 实现的一个有用基类。
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    // 目标对象，即要调用方法的对象实例
    protected final Object target;
    // 要调用的方法对象，通过反射机制来表示具体的方法
    protected final Method method;
    // 调用方法时传入的参数数组，用于传递给目标方法
    protected final Object[] arguments;

    /**
     * 构造函数，用于初始化 ReflectiveMethodInvocation 对象。
     *
     * @param target 目标对象
     * @param method 要调用的方法
     * @param arguments 调用方法时的入参
     */
    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    /**
     * 获取当前调用的方法对象。
     *
     * @return 表示当前调用方法的 Method 对象
     */
    @Override
    public Method getMethod() {
        return method;
    }

    /**
     * 获取调用方法时传入的参数数组。
     *
     * @return 包含调用方法所需参数的数组
     */
    @Override
    public Object[] getArguments() {
        return arguments;
    }

    /**
     * 执行目标方法的核心方法。
     * 使用 Java 的反射机制，通过 Method 对象的 invoke 方法调用目标对象的指定方法，
     * 并传入相应的参数。
     *
     * @return 目标方法的返回值
     * @throws Throwable 如果在方法调用过程中出现异常，将抛出该异常
     */
    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    /**
     * 获取目标对象本身。
     *
     * @return 目标对象实例
     */
    @Override
    public Object getThis() {
        return target;
    }

    /**
     * 获取静态部分，这里返回当前调用的方法对象。
     * 该方法返回的对象通常代表调用的静态元数据，在这种情况下就是 Method 对象。
     *
     * @return 表示当前调用方法的 AccessibleObject（这里实际是 Method 对象）
     */
    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }

}
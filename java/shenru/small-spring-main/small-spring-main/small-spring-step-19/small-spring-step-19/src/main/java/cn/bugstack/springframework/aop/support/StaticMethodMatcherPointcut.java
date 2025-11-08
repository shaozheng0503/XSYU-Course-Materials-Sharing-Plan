package cn.bugstack.springframework.aop.support;

import cn.bugstack.springframework.aop.ClassFilter;
import cn.bugstack.springframework.aop.MethodMatcher;
import cn.bugstack.springframework.aop.Pointcut;

/**
 * @author zhangdd on 2022/2/27
 *
 * 该抽象类 StaticMethodMatcherPointcut 继承自 StaticMethodMatcher 并实现了 Pointcut 接口。
 * 它主要用于定义一个静态的方法匹配切点，即匹配不依赖于运行时参数的方法。
 */
public abstract class StaticMethodMatcherPointcut extends StaticMethodMatcher implements Pointcut {

    // 类过滤器，用于筛选哪些类的方法需要进行匹配检查。
    // 初始值为 ClassFilter.TRUE，表示默认匹配所有类。
    private ClassFilter classFilter = ClassFilter.TRUE;

    /**
     * 设置类过滤器
     *
     * @param classFilter 要设置的类过滤器实例
     */
    public void setClassFilter(ClassFilter classFilter) {
        this.classFilter = classFilter;
    }

    /**
     * 获取类过滤器
     *
     * @return 当前使用的类过滤器实例
     */
    public ClassFilter getClassFilter() {
        return classFilter;
    }

    /**
     * 实现 Pointcut 接口的方法，返回方法匹配器。
     * 由于该类继承自 StaticMethodMatcher，而 StaticMethodMatcher 本身就是一种方法匹配器，
     * 所以直接返回 this，即当前对象作为方法匹配器。
     *
     * @return 方法匹配器实例
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
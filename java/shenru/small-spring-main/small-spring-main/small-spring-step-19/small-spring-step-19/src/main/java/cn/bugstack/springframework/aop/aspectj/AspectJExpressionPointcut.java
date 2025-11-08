package cn.bugstack.springframework.aop.aspectj;

import cn.bugstack.springframework.aop.ClassFilter;
import cn.bugstack.springframework.aop.MethodMatcher;
import cn.bugstack.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring {@link cn.bugstack.springframework.aop.Pointcut} implementation
 * that uses the AspectJ weaver to evaluate a pointcut expression.
 * <p>
 * 切点表达式
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    // 定义一个静态的集合，用于存储支持的切点原语
    // 切点原语是AspectJ中用于定义切点表达式的基本元素
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    // 静态代码块，在类加载时执行，用于初始化支持的切点原语集合
    static {
        // 这里只添加了EXECUTION原语，表示支持方法执行连接点的匹配
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    // 存储解析后的切点表达式对象
    private final PointcutExpression pointcutExpression;

    /**
     * 构造函数，用于初始化切点表达式
     * @param expression 传入的切点表达式字符串
     */
    public AspectJExpressionPointcut(String expression) {
        // 创建一个PointcutParser对象，该对象支持指定的切点原语，并使用当前类的类加载器进行解析
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        // 使用PointcutParser对象解析传入的切点表达式字符串，得到PointcutExpression对象
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    /**
     * 判断给定的类是否匹配切点表达式
     * @param clazz 要进行匹配的类
     * @return 如果该类可能包含匹配切点表达式的连接点，则返回true；否则返回false
     */
    @Override
    public boolean matches(Class<?> clazz) {
        // 调用PointcutExpression对象的couldMatchJoinPointsInType方法进行类的匹配判断
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    /**
     * 判断给定的方法是否匹配切点表达式
     * @param method 要进行匹配的方法
     * @param targetClass 方法所在的目标类
     * @return 如果该方法总是匹配切点表达式，则返回true；否则返回false
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        // 调用PointcutExpression对象的matchesMethodExecution方法进行方法执行的匹配判断
        // 并调用alwaysMatches方法确保总是匹配
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    /**
     * 获取用于类过滤的ClassFilter对象
     * @return 返回当前对象本身，因为当前类实现了ClassFilter接口
     */
    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    /**
     * 获取用于方法匹配的MethodMatcher对象
     * @return 返回当前对象本身，因为当前类实现了MethodMatcher接口
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

}
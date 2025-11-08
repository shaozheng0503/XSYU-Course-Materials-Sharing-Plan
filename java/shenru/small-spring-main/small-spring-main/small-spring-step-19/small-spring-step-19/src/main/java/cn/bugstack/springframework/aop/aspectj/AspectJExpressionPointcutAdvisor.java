package cn.bugstack.springframework.aop.aspectj;

import cn.bugstack.springframework.aop.Pointcut;
import cn.bugstack.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Spring AOP Advisor that can be used for any AspectJ pointcut expression.
 *
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 用于表示切面的对象，基于 AspectJ 表达式来定义切点
    // 它能帮助确定哪些类和方法会被增强
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法，也就是在切点匹配的连接点处要执行的额外逻辑
    // 例如前置通知、后置通知等
    private Advice advice;
    // 存储 AspectJ 切点表达式的字符串
    // 这个表达式用于精确描述哪些类和方法会触发增强逻辑
    private String expression;

    /**
     * 设置 AspectJ 切点表达式
     *
     * @param expression 要设置的 AspectJ 切点表达式字符串
     */
    public void setExpression(String expression) {
        // 将传入的表达式赋值给成员变量
        this.expression = expression;
    }

    /**
     * 获取 Pointcut 对象，用于定义切点
     *
     * @return 一个 Pointcut 对象，用于描述哪些类和方法会被增强
     */
    @Override
    public Pointcut getPointcut() {
        // 检查 pointcut 对象是否为空
        if (null == pointcut) {
            // 如果为空，则根据存储的表达式创建一个新的 AspectJExpressionPointcut 对象
            pointcut = new AspectJExpressionPointcut(expression);
        }
        // 返回 pointcut 对象
        return pointcut;
    }

    /**
     * 获取具体的拦截方法（通知）
     *
     * @return 一个 Advice 对象，代表在切点匹配处要执行的额外逻辑
     */
    @Override
    public Advice getAdvice() {
        // 返回存储的 Advice 对象
        return advice;
    }

    /**
     * 设置具体的拦截方法（通知）
     *
     * @param advice 要设置的 Advice 对象
     */
    public void setAdvice(Advice advice) {
        // 将传入的 Advice 对象赋值给成员变量
        this.advice = advice;
    }
}
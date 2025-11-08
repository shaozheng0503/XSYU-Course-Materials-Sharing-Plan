package cn.bugstack.springframework.context.annotation;

import cn.bugstack.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.bugstack.springframework.stereotype.Component;
import cn.hutool.core.util.StrUtil;

import java.util.Set;

/**
 * A bean definition scanner that detects bean candidates on the classpath,
 * registering corresponding bean definitions with a given registry ({@code BeanFactory}
 * or {@code ApplicationContext}).
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该类 ClassPathBeanDefinitionScanner 用于在类路径下扫描带有特定注解的类，
 * 将这些类作为 Bean 候选者，并将对应的 BeanDefinition 注册到给定的 BeanDefinitionRegistry 中。
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    // 持有 BeanDefinitionRegistry 的引用，用于注册 BeanDefinition
    private BeanDefinitionRegistry registry;

    /**
     * 构造函数，初始化 ClassPathBeanDefinitionScanner 对象。
     *
     * @param registry BeanDefinition 注册中心
     */
    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 执行扫描操作，扫描指定基础包下的类，并将符合条件的类注册为 BeanDefinition。
     *
     * @param basePackages 要扫描的基础包名数组，可以指定多个包
     */
    public void doScan(String... basePackages) {
        // 遍历每个基础包名
        for (String basePackage : basePackages) {
            // 调用父类的 findCandidateComponents 方法，查找指定包下的候选 BeanDefinition 集合
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            // 遍历候选 BeanDefinition 集合
            for (BeanDefinition beanDefinition : candidates) {
                // 解析 Bean 的作用域，如 singleton、prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    // 如果作用域不为空，设置 BeanDefinition 的作用域
                    beanDefinition.setScope(beanScope);
                }
                // 确定 Bean 的名称，并将 BeanDefinition 注册到注册中心
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }

        // 注册处理注解的 BeanPostProcessor（@Autowired、@Value）
        registry.registerBeanDefinition("cn.bugstack.springframework.context.annotation.internalAutowiredAnnotationProcessor",
                new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    /**
     * 解析 Bean 的作用域。
     * 检查 Bean 类上是否有 @Scope 注解，如果有则返回注解的值，否则返回空字符串。
     *
     * @param beanDefinition 要解析的 BeanDefinition
     * @return Bean 的作用域，如果没有则返回空字符串
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        // 获取 Bean 的类信息
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 获取类上的 @Scope 注解
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            // 如果注解存在，返回注解的值
            return scope.value();
        }
        // 注解不存在，返回空字符串
        return StrUtil.EMPTY;
    }

    /**
     * 确定 Bean 的名称。
     * 检查 Bean 类上是否有 @Component 注解，如果有且注解的 value 属性不为空，则使用该值作为 Bean 名称；
     * 否则使用类名的首字母小写作为 Bean 名称。
     *
     * @param beanDefinition 要确定名称的 BeanDefinition
     * @return Bean 的名称
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        // 获取 Bean 的类信息
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 获取类上的 @Component 注解
        Component component = beanClass.getAnnotation(Component.class);
        // 获取注解的 value 属性值
        String value = component
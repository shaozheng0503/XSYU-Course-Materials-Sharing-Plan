package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.FactoryBean;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.config.BeanPostProcessor;
import cn.bugstack.springframework.beans.factory.config.ConfigurableBeanFactory;
import cn.bugstack.springframework.core.convert.ConversionService;
import cn.bugstack.springframework.util.ClassUtils;
import cn.bugstack.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 * <p>
 * BeanDefinition注册表接口
 *
 * 该抽象类 AbstractBeanFactory 是 Spring 框架中 Bean 工厂的核心抽象类，
 * 继承自 FactoryBeanRegistrySupport 并实现了 ConfigurableBeanFactory 接口，
 * 它提供了获取 Bean 的基本实现，同时支持 FactoryBean 的处理、BeanPostProcessor 的应用、
 * 字符串值解析和类型转换等功能。
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /**
     * ClassLoader to resolve bean class names with, if necessary
     * 用于解析 Bean 类名的类加载器，默认使用 ClassUtils 获取的默认类加载器
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * BeanPostProcessors to apply in createBean
     * 存储在创建 Bean 过程中要应用的 BeanPostProcessor 列表，
     * BeanPostProcessor 可以在 Bean 的初始化前后进行额外的处理
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    /**
     * String resolvers to apply e.g. to annotation attribute values
     * 存储用于解析嵌入式字符串值的解析器列表，
     * 例如可以用于解析注解属性值中的占位符等
     */
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    // 类型转换服务，用于在 Bean 属性赋值时进行类型转换
    private ConversionService conversionService;

    /**
     * 根据 Bean 名称获取 Bean 实例
     *
     * @param name Bean 的名称
     * @return Bean 实例
     * @throws BeansException 如果获取 Bean 过程中出现异常
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 根据 Bean 名称和参数获取 Bean 实例
     *
     * @param name Bean 的名称
     * @param args 创建 Bean 时的参数
     * @return Bean 实例
     * @throws BeansException 如果获取 Bean 过程中出现异常
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    /**
     * 根据 Bean 名称和所需类型获取 Bean 实例
     *
     * @param name         Bean 的名称
     * @param requiredType 所需 Bean 的类型
     * @param <T>          Bean 的类型
     * @return 符合类型要求的 Bean 实例
     * @throws BeansException 如果获取 Bean 过程中出现异常
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 检查 Bean 工厂中是否包含指定名称的 Bean
     *
     * @param name Bean 的名称
     * @return 如果包含则返回 true，否则返回 false
     */
    @Override
    public boolean containsBean(String name) {
        return containsBeanDefinition(name);
    }

    /**
     * 检查是否包含指定名称的 BeanDefinition，该方法由子类实现
     *
     * @param beanName Bean 的名称
     * @return 如果包含则返回 true，否则返回 false
     */
    protected abstract boolean containsBeanDefinition(String beanName);

    /**
     * 实际获取 Bean 实例的方法
     *
     * @param name Bean 的名称
     * @param args 创建 Bean 时的参数
     * @param <T>  Bean 的类型
     * @return Bean 实例
     */
    protected <T> T doGetBean(final String name, final Object[] args) {
        // 尝试从单例缓存中获取 Bean 实例
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject 方法获取实际的 Bean 对象
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        // 获取 BeanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(name);
        // 创建 Bean 实例
        Object bean = createBean(name, beanDefinition, args);
        // 如果是 FactoryBean，处理并返回实际的 Bean 对象
        return (T) getObjectForBeanInstance(bean, name);
    }

    /**
     * 根据 Bean 实例和 Bean 名称获取实际的 Bean 对象，如果是 FactoryBean 则调用其 getObject 方法
     *
     * @param beanInstance Bean 实例
     * @param beanName     Bean 的名称
     * @return 实际的 Bean 对象
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        // 如果不是 FactoryBean，直接返回该实例
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        // 尝试从 FactoryBean 缓存中获取对象
        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            // 如果缓存中没有，将实例转换为 FactoryBean 类型
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            // 从 FactoryBean 中获取对象
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    /**
     * 根据 Bean 名称获取 BeanDefinition，该方法由子类实现
     *
     * @param beanName Bean 的名称
     * @return BeanDefinition 对象
     * @throws BeansException 如果获取 BeanDefinition 过程中出现异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 根据 Bean 名称、BeanDefinition 和参数创建 Bean 实例，该方法由子类实现
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition BeanDefinition 对象
     * @param args           创建 Bean 时的参数
     * @return 创建好的 Bean 实例
     * @throws BeansException 如果创建 Bean 过程中出现异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    /**
     * 添加 BeanPostProcessor 到列表中，如果已存在则先移除再添加
     *
     * @param beanPostProcessor 要添加的 BeanPostProcessor
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 添加字符串值解析器到列表中
     *
     * @param valueResolver 要添加的字符串值解析器
     */
    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    /**
     * 解析嵌入式字符串值，依次使用所有的字符串值解析器进行解析
     *
     * @param value 要解析的字符串值
     * @return 解析后的字符串值
     */
    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    /**
     * 设置类型转换服务
     *
     * @param conversionService 要设置的类型转换服务
     */
    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * 获取类型转换服务
     *
     * @return 类型转换服务对象
     */
    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * 获取 BeanPostProcessor 列表
     *
     * @return BeanPostProcessor 列表
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    /**
     * 获取用于解析 Bean 类名的类加载器
     *
     * @return 类加载器对象
     */
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

}
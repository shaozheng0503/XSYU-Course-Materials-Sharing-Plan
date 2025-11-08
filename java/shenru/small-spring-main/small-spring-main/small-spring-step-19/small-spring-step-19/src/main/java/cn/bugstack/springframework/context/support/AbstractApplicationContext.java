package cn.bugstack.springframework.context.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.bugstack.springframework.beans.factory.config.BeanPostProcessor;
import cn.bugstack.springframework.context.ApplicationEvent;
import cn.bugstack.springframework.context.ApplicationListener;
import cn.bugstack.springframework.context.ConfigurableApplicationContext;
import cn.bugstack.springframework.context.event.ApplicationEventMulticaster;
import cn.bugstack.springframework.context.event.ContextClosedEvent;
import cn.bugstack.springframework.context.event.ContextRefreshedEvent;
import cn.bugstack.springframework.context.event.SimpleApplicationEventMulticaster;
import cn.bugstack.springframework.core.convert.ConversionService;
import cn.bugstack.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * Abstract implementation of the {@link cn.bugstack.springframework.context.ApplicationContext}
 * interface. Doesn't mandate the type of storage used for configuration; simply
 * implements common context functionality. Uses the Template Method design pattern,
 * requiring concrete subclasses to implement abstract methods.
 * <p>
 * 抽象应用上下文
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    // 定义应用事件多播器的 Bean 名称，用于在 BeanFactory 中注册和查找该 Bean
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    // 应用事件多播器，用于管理和发布应用事件
    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * 刷新应用上下文，初始化 BeanFactory 并完成一系列的初始化操作
     * @throws BeansException 如果在刷新过程中出现 Bean 相关的异常
     */
    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        // 此方法由具体子类实现，用于创建并初始化 BeanFactory，同时加载 Bean 的定义信息
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        // 从子类实现中获取创建好的可配置的 BeanFactory，后续的操作将基于此 BeanFactory 进行
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        // ApplicationContextAwareProcessor 是一个 BeanPostProcessor，它会在 Bean 初始化前后进行处理
        // 当 Bean 实现了 ApplicationContextAware 接口时，会将当前的 ApplicationContext 注入到 Bean 中
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        // BeanFactoryPostProcessor 可以在 Bean 定义加载完成后，但在 Bean 实例化之前对 BeanFactory 进行修改
        // 此方法会获取所有的 BeanFactoryPostProcessor 并依次调用其 postProcessBeanFactory 方法
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        // BeanPostProcessor 用于在 Bean 初始化前后进行额外的处理，如增强 Bean 的功能等
        // 此方法会获取所有的 BeanPostProcessor 并注册到 BeanFactory 中
        registerBeanPostProcessors(beanFactory);

        // 6. 初始化事件发布者
        // 初始化应用事件多播器，并将其注册到 BeanFactory 中，用于后续的事件发布和监听
        initApplicationEventMulticaster();

        // 7. 注册事件监听器
        // 从 BeanFactory 中获取所有的 ApplicationListener，并将其注册到应用事件多播器中
        registerListeners();

        // 8. 设置类型转换器、提前实例化单例Bean对象
        // 此方法会检查 BeanFactory 中是否存在类型转换器，并将其设置到 BeanFactory 中
        // 同时会提前实例化所有的单例 Bean
        finishBeanFactoryInitialization(beanFactory);

        // 9. 发布容器刷新完成事件
        // 当所有的初始化操作完成后，发布容器刷新完成事件，通知所有的监听器
        finishRefresh();
    }

    /**
     * 设置类型转换器、提前实例化单例Bean对象
     * @param beanFactory 可配置的 BeanFactory
     */
    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        // 设置类型转换器
        // 检查 BeanFactory 中是否包含名为 "conversionService" 的 Bean
        if (beanFactory.containsBean("conversionService")) {
            // 获取该 Bean 实例
            Object conversionService = beanFactory.getBean("conversionService");
            // 检查该 Bean 是否实现了 ConversionService 接口
            if (conversionService instanceof ConversionService) {
                // 如果实现了，则将其设置为 BeanFactory 的类型转换器
                beanFactory.setConversionService((ConversionService) conversionService);
            }
        }

        // 提前实例化单例Bean对象
        // 调用 BeanFactory 的 preInstantiateSingletons 方法，将所有的单例 Bean 提前实例化
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 创建 BeanFactory 并加载 BeanDefinition，由具体子类实现
     * @throws BeansException 如果在创建过程中出现 Bean 相关的异常
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取可配置的 BeanFactory，由具体子类实现
     * @return 可配置的 BeanFactory
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 执行所有的 BeanFactoryPostProcessor
     * @param beanFactory 可配置的 BeanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        // 从 BeanFactory 中获取所有实现了 BeanFactoryPostProcessor 接口的 Bean
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        // 遍历这些 BeanFactoryPostProcessor
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            // 调用每个 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法，对 BeanFactory 进行修改
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册所有的 BeanPostProcessor
     * @param beanFactory 可配置的 BeanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        // 从 BeanFactory 中获取所有实现了 BeanPostProcessor 接口的 Bean
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        // 遍历这些 BeanPostProcessor
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            // 将每个 BeanPostProcessor 注册到 BeanFactory 中
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 初始化应用事件多播器
     */
    private void initApplicationEventMulticaster() {
        // 获取可配置的 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 创建一个简单的应用事件多播器实例
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        // 将应用事件多播器作为单例 Bean 注册到 BeanFactory 中
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 注册所有的事件监听器
     */
    private void registerListeners() {
        // 从 BeanFactory 中获取所有实现了 ApplicationListener 接口的 Bean
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        // 遍历这些事件监听器
        for (ApplicationListener listener : applicationListeners) {
            // 将每个事件监听器注册到应用事件多播器中
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    /**
     * 完成容器刷新操作，发布容器刷新完成事件
     */
    private void finishRefresh() {
        // 创建一个容器刷新完成事件实例
        ContextRefreshedEvent event = new ContextRefreshedEvent(this);
        // 调用应用事件多播器的 multicastEvent 方法发布该事件
        publishEvent(event);
    }

    /**
     * 发布应用事件
     * @param event 要发布的应用事件
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        // 调用应用事件多播器的 multicastEvent 方法，将事件广播给所有的监听器
        applicationEventMulticaster.multicastEvent(event);
    }

    /**
     * 根据类型获取所有的 Bean
     * @param type Bean 的类型
     * @param <T> Bean 的泛型类型
     * @return 包含 Bean 名称和实例的 Map
     * @throws BeansException 如果在获取过程中出现 Bean 相关的异常
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        // 调用 BeanFactory 的 getBeansOfType 方法获取指定类型的所有 Bean
        return getBeanFactory().getBeansOfType(type);
    }

    /**
     * 获取所有 Bean 定义的名称
     * @return Bean 定义名称的数组
     */
    @Override
    public String[] getBeanDefinitionNames() {
        // 调用 BeanFactory 的 getBeanDefinitionNames 方法获取所有 Bean 定义的名称
        return getBeanFactory().getBeanDefinitionNames();
    }

    /**
     * 根据名称获取 Bean 实例
     * @param name Bean 的名称
     * @return Bean 实例
     * @throws BeansException 如果在获取过程中出现 Bean 相关的异常
     */
    @Override
    public Object getBean(String name) throws BeansException {
        // 调用 BeanFactory 的 getBean 方法获取指定名称的 Bean 实例
        return getBeanFactory().getBean(name);
    }

    /**
     * 根据名称和参数获取 Bean 实例
     * @param name Bean 的名称
     * @param args 创建 Bean 时使用的参数
     * @return Bean 实例
     * @throws BeansException 如果在获取过程中出现 Bean 相关的异常
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        // 调用 BeanFactory 的 getBean 方法获取指定名称和参数的 Bean 实例
        return getBeanFactory().getBean(name, args);
    }

    /**
     * 根据名称和类型获取 Bean 实例
     * @param name Bean 的名称
     * @param requiredType Bean 的类型
     * @param <T> Bean 的泛型类型
     * @return Bean 实例
     * @throws BeansException 如果在获取过程中出现 Bean 相关的异常
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        // 调用 BeanFactory 的 getBean 方法获取指定名称和类型的 Bean 实例
        return getBeanFactory().getBean(name, requiredType);
    }

    /**
     * 根据类型获取 Bean 实例
     * @param requiredType Bean 的类型
     * @param <T> Bean 的泛型类型
     * @return Bean 实例
     * @throws BeansException 如果在获取过程中出现 Bean 相关的异常
     */
    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        // 调用 BeanFactory 的 getBean 方法获取指定类型的 Bean 实例
        return getBeanFactory().getBean(requiredType);
    }

    /**
     * 检查 BeanFactory 中是否包含指定名称的 Bean
     * @param name Bean 的名称
     * @return 如果包含则返回 true，否则返回 false
     */
    @Override
    public boolean containsBean(String name) {
        // 调用 BeanFactory 的 containsBean 方法检查是否包含指定名称的 Bean
        return getBeanFactory().containsBean(name);
    }

    /**
     * 注册关闭钩子，当 JVM 关闭时自动调用关闭方法
     */
    @Override
    public void registerShutdownHook() {
        // 获取当前运行时环境，添加一个关闭钩子线程
        // 当 JVM 关闭时，该线程会调用当前对象的 close 方法
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    /**
     * 关闭应用上下文，发布容器关闭事件并销毁所有单例 Bean
     */
    @Override
    public void close() {
        // 发布容器关闭事件
        // 创建一个容器关闭事件实例，并调用 publishEvent 方法发布该事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例 bean 的销毁方法
        // 调用 BeanFactory 的 destroySingletons 方法，销毁所有的单例 Bean
        getBeanFactory().destroySingletons();
    }

}
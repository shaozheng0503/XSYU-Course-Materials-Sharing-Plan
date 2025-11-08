package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.PropertyValue;
import cn.bugstack.springframework.beans.PropertyValues;
import cn.bugstack.springframework.beans.factory.*;
import cn.bugstack.springframework.beans.factory.config.*;
import cn.bugstack.springframework.context.ApplicationContextAware;
import cn.bugstack.springframework.core.convert.ConversionService;
import cn.bugstack.springframework.util.ClassUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.BasicType;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Abstract bean factory superclass that implements default bean creation,
 * with the full capabilities specified by the class.
 * Implements the {@link cn.bugstack.springframework.beans.factory.config.AutowireCapableBeanFactory}
 * interface in addition to AbstractBeanFactory's {@link #createBean} method.
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该抽象类继承自 AbstractBeanFactory 并实现了 AutowireCapableBeanFactory 接口，
 * 主要负责实现默认的 Bean 创建逻辑，具备完整的 Bean 创建能力。
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    // 实例化策略，默认为 SimpleInstantiationStrategy
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    /**
     * 重写父类的 createBean 方法，创建 Bean 实例。
     * 首先尝试解析是否需要返回代理 Bean 对象，如果需要则直接返回，否则调用 doCreateBean 方法进行创建。
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition Bean 的定义信息
     * @param args           创建 Bean 时的参数
     * @return 创建好的 Bean 实例
     * @throws BeansException 如果创建过程中出现异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        // 判断是否返回代理 Bean 对象
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (null != bean) {
            return bean;
        }

        return doCreateBean(beanName, beanDefinition, args);
    }

    /**
     * 实际创建 Bean 的方法，包含了 Bean 创建的完整流程。
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition Bean 的定义信息
     * @param args           创建 Bean 时的参数
     * @return 创建好的 Bean 实例
     * @throws BeansException 如果创建过程中出现异常
     */
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            // 实例化 Bean
            bean = createBeanInstance(beanDefinition, beanName, args);

            // 处理循环依赖，将实例化后的 Bean 对象提前放入缓存中暴露出来
            if (beanDefinition.isSingleton()) {
                Object finalBean = bean;
                addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, beanDefinition, finalBean));
            }

            // 实例化后判断
            boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName, bean);
            if (!continueWithPropertyPopulation) {
                return bean;
            }
            // 在设置 Bean 属性之前，允许 BeanPostProcessor 修改属性值
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        // 判断 SCOPE_SINGLETON、SCOPE_PROTOTYPE
        Object exposedObject = bean;
        if (beanDefinition.isSingleton()) {
            // 获取代理对象
            exposedObject = getSingleton(beanName);
            registerSingleton(beanName, exposedObject);
        }
        return exposedObject;

    }

    /**
     * 获取早期 Bean 引用，用于处理循环依赖。
     * 遍历所有的 BeanPostProcessor，调用其中实现了 InstantiationAwareBeanPostProcessor 接口的处理器的
     * getEarlyBeanReference 方法，获取早期 Bean 引用。
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition Bean 的定义信息
     * @param bean           Bean 实例
     * @return 早期 Bean 引用
     */
    protected Object getEarlyBeanReference(String beanName, BeanDefinition beanDefinition, Object bean) {
        Object exposedObject = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                exposedObject = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).getEarlyBeanReference(exposedObject, beanName);
                if (null == exposedObject) return exposedObject;
            }
        }

        return exposedObject;
    }

    /**
     * Bean 实例化后对于返回 false 的对象，不再执行后续设置 Bean 对象属性的操作。
     * 遍历所有的 BeanPostProcessor，调用其中实现了 InstantiationAwareBeanPostProcessor 接口的处理器的
     * postProcessAfterInstantiation 方法，根据返回值决定是否继续属性填充。
     *
     * @param beanName Bean 的名称
     * @param bean     Bean 实例
     * @return 是否继续进行属性填充
     */
    private boolean applyBeanPostProcessorsAfterInstantiation(String beanName, Object bean) {
        boolean continueWithPropertyPopulation = true;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
                if (!instantiationAwareBeanPostProcessor.postProcessAfterInstantiation(bean, beanName)) {
                    continueWithPropertyPopulation = false;
                    break;
                }
            }
        }
        return continueWithPropertyPopulation;
    }

    /**
     * 在设置 Bean 属性之前，允许 BeanPostProcessor 修改属性值。
     * 遍历所有的 BeanPostProcessor，调用其中实现了 InstantiationAwareBeanPostProcessor 接口的处理器的
     * postProcessPropertyValues 方法，将处理后的属性值更新到 BeanDefinition 中。
     *
     * @param beanName       Bean 的名称
     * @param bean           Bean 实例
     * @param beanDefinition Bean 的定义信息
     */
    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (null != pvs) {
                    for (PropertyValue propertyValue : pvs.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

    /**
     * 在实例化 Bean 之前进行解析，尝试获取代理 Bean 对象。
     * 先调用 applyBeanPostProcessorsBeforeInstantiation 方法获取可能的代理 Bean，
     * 如果获取到则调用 applyBeanPostProcessorsAfterInitialization 方法进行初始化后的处理。
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition Bean 的定义信息
     * @return 解析后的 Bean 实例
     */
    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (null != bean) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    /**
     * 在 Bean 实例化之前应用 BeanPostProcessor。
     * 遍历所有的 BeanPostProcessor，调用其中实现了 InstantiationAwareBeanPostProcessor 接口的处理器的
     * postProcessBeforeInstantiation 方法，如果返回非空结果则直接返回。
     *
     * @param beanClass Bean 的类信息
     * @param beanName  Bean 的名称
     * @return 处理后的 Bean 实例
     */
    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (null != result) return result;
            }
        }
        return null;
    }

    /**
     * 如果必要，注册可销毁的 Bean。
     * 对于单例 Bean，判断其是否实现了 DisposableBean 接口或配置了销毁方法，如果是则注册为可销毁 Bean。
     *
     * @param beanName       Bean 的名称
     * @param bean           Bean 实例
     * @param beanDefinition Bean 的定义信息
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 非 Singleton 类型的 Bean 不执行销毁方法
        if (!beanDefinition.isSingleton()) return;

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    /**
     * 创建 Bean 实例。
     * 根据构造函数参数匹配合适的构造函数，然后使用实例化策略创建 Bean 实例。
     *
     * @param beanDefinition Bean 的定义信息
     * @param beanName       Bean 的名称
     * @param args           创建 Bean 时的参数
     * @return 创建好的 Bean 实例
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * Bean 属性填充。
     * 遍历 BeanDefinition 中的属性值，处理依赖引用和类型转换，然后使用反射设置属性值。
     *
     * @param beanName       Bean 的名称
     * @param bean           Bean 实例
     * @param beanDefinition Bean 的定义信息
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 类型转换
                else {
                    Class<?> sourceType = value.getClass();
                    Class<?> targetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(), name);
                    ConversionService conversionService = getConversionService();
                    if (conversionService != null) {
                        if (conversionService.canConvert(sourceType, targetType)) {
                            value = conversionService.convert(value, targetType);
                        }
                    }
                }

                // 反射设置属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName + " message：" + e);
        }
    }

    /**
     * 获取实例化策略。
     *
     * @return 实例化策略对象
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 设置实例化策略。
     *
     * @param instantiationStrategy 要设置的实例化策略对象
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * 初始化 Bean。
     * 首先处理 Aware 接口的回调，然后执行 BeanPostProcessor 的前置处理，接着调用初始化方法，
     * 最后执行 BeanPostProcessor 的后置处理。
     *
     * @param beanName       Bean 的名称
     * @param bean           Bean 实例
     * @param beanDefinition Bean 的定义信息
     * @return 初始化后的 Bean 实例
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {

        // invokeAwareMethods
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 执行 Bean 对象的初始化方法
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    /**
     * 调用 Bean 的初始化方法。
     * 首先检查 Bean 是否实现了 InitializingBean 接口，如果是则调用其 afterPropertiesSet 方法，
     * 然后检查是否配置了 init-method，如果有则通过反射调用该方法。
     *
     * @param beanName       Bean 的名称
     * @param bean           Bean 实例
     * @param beanDefinition Bean 的定义信息
     * @throws Exception 如果调用初始化方法时出现异常
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 2. 注解配置 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    /**
     * 在 Bean 初始化之前应用 BeanPostProcessor。
     * 遍历所有的 BeanPostProcessor，依次调用其 postProcessBeforeInitialization 方法，
     * 将处理后的结果返回。
     *
     * @param existingBean 已存在的 Bean 实例
     * @param beanName     Bean 的名称
     * @return 处理后的 Bean 实例
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

/**
 * 在 Bean 初始化之后应用 BeanPostProcessor。
 * 遍历所有的 BeanPostProcessor，依次调用其 postProcessAfterInitialization 方法，
 * 将处理后的结果返回。
 *
 * @param existingBean 已存在的 Bean 实例
 * @param beanName     Bean 的名称
 * @return 处理后的 Bean 实例
 @throws BeansException 如果处理过程中出现异常
 */
@Override
public Object applyBeanPostProcessorsAfterInitialization (Object existingBean, String beanName) throws BeansException {
    Object result = existingBean;
    for (BeanPostProcessor processor : getBeanPostProcessors ()) {
        Object current = processor.postProcessAfterInitialization (result, beanName);
        if (null == current) return result;
        result = current;
    }
    return result;
}
}
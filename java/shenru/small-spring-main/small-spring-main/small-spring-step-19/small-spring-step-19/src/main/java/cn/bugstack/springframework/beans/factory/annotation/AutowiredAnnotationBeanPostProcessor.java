package cn.bugstack.springframework.beans.factory.annotation;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.PropertyValues;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.BeanFactoryAware;
import cn.bugstack.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.bugstack.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.bugstack.springframework.core.convert.ConversionService;
import cn.bugstack.springframework.util.ClassUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;

import java.lang.reflect.Field;

/**
 * {@link cn.bugstack.springframework.beans.factory.config.BeanPostProcessor} implementation
 * that autowires annotated fields, setter methods and arbitrary config methods.
 * Such members to be injected are detected through a Java 5 annotation: by default,
 * Spring's {@link Autowired @Autowired} and {@link Value @Value} annotations.
 * <p>
 * 处理 @Value、@Autowired，注解的 BeanPostProcessor
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该类是一个 Bean 后置处理器，用于处理 @Value 和 @Autowired 注解，
 * 实现对 Bean 中使用这些注解标注的字段进行自动注入的功能。
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    // 持有 ConfigurableListableBeanFactory 的引用，用于获取 Bean、解析值和类型转换服务等操作
    private ConfigurableListableBeanFactory beanFactory;

    /**
     * 实现 BeanFactoryAware 接口的方法，设置 BeanFactory。
     *
     * @param beanFactory 要设置的 BeanFactory
     * @throws BeansException 如果设置过程中出现异常
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // 将传入的 BeanFactory 转换为 ConfigurableListableBeanFactory 并赋值给成员变量
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    /**
     * 在 Bean 属性赋值时进行后置处理，处理 @Value 和 @Autowired 注解。
     *
     * @param pvs 要处理的属性值
     * @param bean Bean 对象
     * @param beanName Bean 的名称
     * @return 处理后的属性值
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 1. 处理注解 @Value
        // 获取 Bean 的类信息
        Class<?> clazz = bean.getClass();
        // 如果是 CGLIB 代理类，获取其原始父类
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        // 获取类中声明的所有字段
        Field[] declaredFields = clazz.getDeclaredFields();

        // 遍历所有字段
        for (Field field : declaredFields) {
            // 检查字段上是否有 @Value 注解
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                // 获取 @Value 注解的值
                Object value = valueAnnotation.value();
                // 解析注解值中的占位符
                value = beanFactory.resolveEmbeddedValue((String) value);

                // 类型转换
                // 获取注解值的原始类型
                Class<?> sourceType = value.getClass();
                // 获取字段的目标类型
                Class<?> targetType = (Class<?>) TypeUtil.getType(field);
                // 从 BeanFactory 中获取类型转换服务
                ConversionService conversionService = beanFactory.getConversionService();
                if (conversionService != null) {
                    // 检查是否可以进行类型转换
                    if (conversionService.canConvert(sourceType, targetType)) {
                        // 进行类型转换
                        value = conversionService.convert(value, targetType);
                    }
                }

                // 使用 BeanUtil 工具类将转换后的值设置到 Bean 的字段中
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // 2. 处理注解 @Autowired
        // 再次遍历所有字段
        for (Field field : declaredFields) {
            // 检查字段上是否有 @Autowired 注解
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                // 获取字段的类型
                Class<?> fieldType = field.getType();
                // 用于存储依赖 Bean 的名称
                String dependentBeanName = null;
                // 用于存储依赖的 Bean 实例
                Object dependentBean = null;
                // 检查字段上是否有 @Qualifier 注解
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                if (null != qualifierAnnotation) {
                    // 获取 @Qualifier 注解指定的 Bean 名称
                    dependentBeanName = qualifierAnnotation.value();
                    // 根据名称和类型从 BeanFactory 中获取依赖的 Bean 实例
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    // 如果没有 @Qualifier 注解，直接根据类型从 BeanFactory 中获取依赖的 Bean 实例
                    dependentBean = beanFactory.getBean(fieldType);
                }
                // 使用 BeanUtil 工具类将依赖的 Bean 实例设置到 Bean 的字段中
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }

        // 返回原始的属性值，因为这里主要是处理注解注入，不修改属性值对象
        return pvs;
    }

    /**
     * 在 Bean 实例化之前执行的后置处理方法。
     *
     * @param beanClass Bean 的类
     * @param beanName Bean 的名称
     * @return 返回 null 表示不进行特殊处理，继续正常的实例化流程
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    /**
     * 在 Bean 实例化之后执行的后置处理方法。
     *
     * @param bean 实例化后的 Bean 对象
     * @param beanName Bean 的名称
     * @return 返回 true 表示继续进行属性填充等后续操作
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    /**
     * 在 Bean 初始化之前执行的后置处理方法。
     *
     * @param bean 要处理的 Bean 对象
     * @param beanName Bean 的名称
     * @return 直接返回 null 表示不进行特殊处理
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    /**
     * 在 Bean 初始化之后执行的后置处理方法。
     *
     * @param bean 初始化后的 Bean 对象
     * @param beanName Bean 的名称
     * @return 直接返回 null 表示不进行特殊处理
     * @throws BeansException 如果处理过程中出现异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
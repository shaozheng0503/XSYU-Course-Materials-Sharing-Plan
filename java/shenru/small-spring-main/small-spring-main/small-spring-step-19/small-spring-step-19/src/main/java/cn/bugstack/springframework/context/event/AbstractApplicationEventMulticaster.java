package cn.bugstack.springframework.context.event;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.BeanFactoryAware;
import cn.bugstack.springframework.context.ApplicationEvent;
import cn.bugstack.springframework.context.ApplicationListener;
import cn.bugstack.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Abstract implementation of the {@link ApplicationEventMulticaster} interface,
 * providing the basic listener registration facility.
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该抽象类 AbstractApplicationEventMulticaster 实现了 ApplicationEventMulticaster 接口和 BeanFactoryAware 接口，
 * 为应用事件多播器提供了基本的监听器注册功能。它可以管理应用程序中的事件监听器，
 * 并根据事件类型筛选出感兴趣的监听器。
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    // 存储应用程序事件监听器的集合，使用 LinkedHashSet 保证顺序且元素唯一
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    // 持有 BeanFactory 的引用，用于获取 Bean 实例
    private BeanFactory beanFactory;

    /**
     * 向多播器中添加应用程序事件监听器。
     *
     * @param listener 要添加的监听器
     */
    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        // 将监听器添加到集合中
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    /**
     * 从多播器中移除应用程序事件监听器。
     *
     * @param listener 要移除的监听器
     */
    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        // 从集合中移除监听器
        applicationListeners.remove(listener);
    }

    /**
     * 实现 BeanFactoryAware 接口的方法，设置 BeanFactory。
     *
     * @param beanFactory 要设置的 BeanFactory
     */
    @Override
    public final void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 根据给定的事件类型，返回与之匹配的应用程序事件监听器集合。
     * 不匹配的监听器会被提前排除。
     *
     * @param event 要传播的事件，用于根据缓存的匹配信息提前排除不匹配的监听器
     * @return 匹配的应用程序事件监听器集合
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        // 创建一个 LinkedList 用于存储匹配的监听器
        LinkedList<ApplicationListener> allListeners = new LinkedList<ApplicationListener>();
        // 遍历所有的监听器
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            // 检查监听器是否对该事件感兴趣
            if (supportsEvent(listener, event)) {
                // 如果感兴趣，将监听器添加到集合中
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * 判断监听器是否对该事件感兴趣。
     *
     * @param applicationListener 要检查的监听器
     * @param event 要判断的事件
     * @return 如果监听器对该事件感兴趣返回 true，否则返回 false
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        // 获取监听器的类信息
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        // 如果是 CGLIB 代理类，获取其超类；否则使用当前类
        Class<?> targetClass = ClassUtils.isCglib        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        // 如果是 CGLIB 代理类，获取其超类；否则使用当前类
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        // 获取监听器类实现的第一个泛型接口
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        // 从泛型接口中获取实际的泛型类型参数，这里假设泛型接口只有一个泛型参数
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        // 获取泛型参数的类型名称
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            // 通过反射根据类型名称获取对应的 Class 对象
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            // 如果找不到对应的类，抛出 BeansException 异常
            throw new BeansException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom 是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是 Object。
        // 如果 A.isAssignableFrom(B) 结果是 true，证明 B 可以转换成为 A，也就是 A 可以由 B 转换而来。
        return eventClassName.isAssignableFrom(event.getClass());
    }

}
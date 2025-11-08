package cn.bugstack.springframework.context.event;

import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.context.ApplicationEvent;
import cn.bugstack.springframework.context.ApplicationListener;

/**
 * Simple implementation of the {@link ApplicationEventMulticaster} interface.
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该类 SimpleApplicationEventMulticaster 是 ApplicationEventMulticaster 接口的简单实现类，
 * 继承自 AbstractApplicationEventMulticaster，主要负责将事件多播给所有对该事件感兴趣的监听器。
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    /**
     * 构造函数，用于初始化 SimpleApplicationEventMulticaster 对象。
     *
     * @param beanFactory Bean 工厂，用于设置到父类中
     */
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        // 调用父类的 setBeanFactory 方法设置 BeanFactory
        setBeanFactory(beanFactory);
    }

    /**
     * 实现事件多播的核心方法，将给定的事件多播给所有对该事件感兴趣的监听器。
     *
     * @param event 要多播的应用程序事件
     */
    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        // 调用父类的 getApplicationListeners 方法，获取所有对该事件感兴趣的监听器
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            // 调用监听器的 onApplicationEvent 方法，将事件传递给监听器进行处理
            listener.onApplicationEvent(event);
        }
    }

}
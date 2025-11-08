package cn.bugstack.springframework.beans.factory.xml;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.PropertyValue;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.config.BeanReference;
import cn.bugstack.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.bugstack.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.bugstack.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import cn.bugstack.springframework.core.io.Resource;
import cn.bugstack.springframework.core.io.ResourceLoader;
import cn.hutool.core.util.StrUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Bean definition reader for XML bean definitions.
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 该类 XmlBeanDefinitionReader 用于从 XML 文件中读取 Bean 定义信息，
 * 并将这些信息注册到 BeanDefinitionRegistry 中。它继承自 AbstractBeanDefinitionReader，
 * 实现了从 XML 资源加载 Bean 定义的功能。
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    /**
     * 构造函数，使用指定的 BeanDefinitionRegistry 初始化 XmlBeanDefinitionReader。
     *
     * @param registry BeanDefinition 注册中心
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 构造函数，使用指定的 BeanDefinitionRegistry 和 ResourceLoader 初始化 XmlBeanDefinitionReader。
     *
     * @param registry       BeanDefinition 注册中心
     * @param resourceLoader 资源加载器
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    /**
     * 从单个资源加载 Bean 定义信息。
     *
     * @param resource 要加载的资源
     * @throws BeansException 如果加载过程中出现异常
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            // 尝试从资源中获取输入流
            try (InputStream inputStream = resource.getInputStream()) {
                // 调用实际加载 Bean 定义的方法
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            // 捕获异常并抛出 BeansException
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 从多个资源加载 Bean 定义信息。
     *
     * @param resources 要加载的资源数组
     * @throws BeansException 如果加载过程中出现异常
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        // 遍历资源数组，逐个调用 loadBeanDefinitions 方法
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    /**
     * 根据资源位置加载 Bean 定义信息。
     *
     * @param location 资源的位置
     * @throws BeansException 如果加载过程中出现异常
     */
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        // 获取资源加载器
        ResourceLoader resourceLoader = getResourceLoader();
        // 根据位置获取资源
        Resource resource = resourceLoader.getResource(location);
        // 调用 loadBeanDefinitions 方法加载资源
        loadBeanDefinitions(resource);
    }

    /**
     * 根据多个资源位置加载 Bean 定义信息。
     *
     * @param locations 资源位置数组
     * @throws BeansException 如果加载过程中出现异常
     */
    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        // 遍历位置数组，逐个调用 loadBeanDefinitions 方法
        for (String location : locations) {
            loadBeanDefinitions(location);
        }}
    /**
     实际执行从输入流中加载 Bean 定义信息的方法。
     @param inputStream 包含 XML 数据的输入流
     @throws ClassNotFoundException 如果找不到类
     @throws DocumentException 如果解析 XML 文档时出现异常
     */
    protected void doLoadBeanDefinitions (InputStream inputStream) throws ClassNotFoundException, DocumentException {
// 创建 SAXReader 对象，用于读取 XML 文档
        SAXReader reader = new SAXReader ();
// 从输入流中读取 XML 文档
        Document document = reader.read (inputStream);
// 获取 XML 文档的根元素
        Element root = document.getRootElement ();
// 解析 context:component-scan 标签，扫描包中的类并提取相关信息，用于组装 BeanDefinition
        Element componentScan = root.element ("component-scan");
        if (null != componentScan) {
// 获取 base-package 属性值
            String scanPath = componentScan.attributeValue ("base-package");
            if (StrUtil.isEmpty (scanPath)) {
// 如果 base-package 属性为空或 null，抛出异常
                throw new BeansException ("The value of base-package attribute can not be empty or null");
            }
// 调用扫描包的方法
            scanPackage (scanPath);
        }
// 获取所有 <bean> 元素
        List<Element> beanList = root.elements ("bean");
        for (Element bean : beanList) {
// 获取 <bean> 元素的 id 属性
            String id = bean.attributeValue ("id");
// 获取 <bean> 元素的 name 属性
            String name = bean.attributeValue ("name");
// 获取 <bean> 元素的 class 属性
            String className = bean.attributeValue ("class");
// 获取 <bean> 元素的 init-method 属性
            String initMethod = bean.attributeValue ("init-method");
// 获取 <bean> 元素的 destroy-method 属性
            String destroyMethodName = bean.attributeValue ("destroy-method");
// 获取 <bean> 元素的 scope 属性
            String beanScope = bean.attributeValue ("scope");
// 通过类名获取 Class 对象
            Class<?> clazz = Class.forName (className);
// 确定 Bean 的名称，优先级 id > name
            String beanName = StrUtil.isNotEmpty (id) ? id : name;
            if (StrUtil.isEmpty (beanName)) {
// 如果 id 和 name 都为空，使用类名的首字母小写作为 Bean 名称
                beanName = StrUtil.lowerFirst (clazz.getSimpleName ());
            }
// 创建 BeanDefinition 对象
            BeanDefinition beanDefinition = new BeanDefinition (clazz);
// 设置初始化方法名
            beanDefinition.setInitMethodName (initMethod);
// 设置销毁方法名
            beanDefinition.setDestroyMethodName (destroyMethodName);
            if (StrUtil.isNotEmpty (beanScope)) {
// 如果 scope 属性不为空，设置 Bean 的作用域
                beanDefinition.setScope (beanScope);
            }
// 获取 <bean> 元素下的所有 <property> 元素
            List<Element> propertyList = bean.elements ("property");
// 读取属性并填充
            for (Element property : propertyList) {
// 解析 <property> 标签的 name 属性
                String attrName = property.attributeValue ("name");
// 解析 <property> 标签的 value 属性
                String attrValue = property.attributeValue ("value");
// 解析 <property> 标签的 ref 属性
                String attrRef = property.attributeValue ("ref");
// 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty (attrRef) ? new BeanReference (attrRef) : attrValue;
// 创建属性信息
                PropertyValue propertyValue = new PropertyValue (attrName, value);
// 将属性信息添加到 BeanDefinition 的属性值列表中
                beanDefinition.getPropertyValues ().addPropertyValue (propertyValue);
            }
            if (getRegistry ().containsBeanDefinition (beanName)) {
// 如果注册中心已经包含该 Bean 名称，抛出异常
                throw new BeansException ("Duplicate beanName [" + beanName + "] is not allowed");
            }
// 注册 BeanDefinition 到注册中心
            getRegistry ().registerBeanDefinition (beanName, beanDefinition);
        }
    }
/**
 扫描指定包下的类，并将符合条件的类注册为 BeanDefinition。
 /**
 * 扫描指定包下的类，并将符合条件的类注册为 BeanDefinition。
 *
 * @param scanPath 要扫描的包路径，多个包路径可以用逗号分隔
 */
private void scanPackage(String scanPath) {
    // 将扫描路径按逗号分割成多个基础包名数组
    String[] basePackages = StrUtil.splitToArray(scanPath, ',');
    // 创建一个类路径 Bean 定义扫描器，传入 BeanDefinition 注册中心
    ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
    // 调用扫描器的 doScan 方法，开始扫描指定基础包下的类，并将符合条件的类注册为 BeanDefinition
    scanner.doScan(basePackages);
}
}
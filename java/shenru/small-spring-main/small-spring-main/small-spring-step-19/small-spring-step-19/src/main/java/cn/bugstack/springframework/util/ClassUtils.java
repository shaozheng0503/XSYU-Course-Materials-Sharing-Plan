package cn.bugstack.springframework.util;

import cn.hutool.core.lang.Assert;

import java.io.Closeable;
import java.io.Externalizable;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * ClassUtils 类提供了一系列用于操作 Java 类和类加载器的工具方法。
 * 这些方法涵盖了类加载器获取、CGLIB 代理类检查、方法名处理、接口获取、类可见性检查等功能，
 * 可以帮助开发者更方便地处理 Java 类相关的操作。
 */
public class ClassUtils {

    // 存储 Java 语言核心接口的集合，这些接口是 Java 语言自带的基础接口
    private static final Set<Class<?>> javaLanguageInterfaces;
    // 缓存常用类的映射，键为类名，值为对应的 Class 对象，提高类查找效率
    private static final Map<String, Class<?>> commonClassCache = new HashMap<>(64);
    // 基本类型到其包装类型的映射，例如 int 到 Integer
    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<>(8);
    // 包装类型到其基本类型的映射，例如 Integer 到 int
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new IdentityHashMap<>(8);

    // 静态代码块，在类加载时执行，用于初始化一些静态变量
    static {
        // 定义 Java 语言核心接口数组
        Class<?>[] javaLanguageInterfaceArray = {Serializable.class, Externalizable.class,
                Closeable.class, AutoCloseable.class, Cloneable.class, Comparable.class};
        // 将这些常用类注册到缓存中
        registerCommonClasses(javaLanguageInterfaceArray);
        // 将接口数组转换为集合存储
        javaLanguageInterfaces = new HashSet<>(Arrays.asList(javaLanguageInterfaceArray));
    }

    /**
     * 将常用类注册到 commonClassCache 中，方便后续快速查找
     *
     * @param commonClasses 要注册的常用类数组
     */
    private static void registerCommonClasses(Class<?>... commonClasses) {
        for (Class<?> clazz : commonClasses) {
            // 将类名和对应的 Class 对象存入缓存
            commonClassCache.put(clazz.getName(), clazz);
        }
    }

    /**
     * 获取默认的类加载器。
     * 首先尝试获取当前线程的上下文类加载器，如果获取失败则使用当前类的类加载器
     *
     * @return 默认的类加载器
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            // 尝试获取当前线程的上下文类加载器
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // 无法访问线程上下文类加载器，捕获异常，后续使用系统类加载器
        }
        if (cl == null) {
            // 没有线程上下文类加载器，使用当前类的类加载器
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    /**
     * 检查指定的类是否是 CGLIB 生成的代理类
     *
     * @param clazz 要检查的类
     * @return 如果是 CGLIB 生成的代理类返回 true，否则返回 false
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * 检查指定的类名是否是 CGLIB 生成的代理类名
     * CGLIB 生成的代理类名通常包含 "$$"
     *
     * @param className 要检查的类名
     * @return 如果是 CGLIB 生成的代理类名返回 true，否则返回 false
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }

    /**
     * 获取方法的全限定名，格式为 类名.方法名
     *
     * @param method 方法对象
     * @param clazz  类对象，如果为 null 则使用方法的声明类
     * @return 方法的全限定名
     */
    public static String getQualifiedMethodName(Method method, Class<?> clazz) {
        // 确保方法对象不为 null
        Assert.notNull(method, "Method must not be null");
        // 如果 clazz 不为 null 则使用 clazz，否则使用方法的声明类
        return (clazz != null ? clazz : method.getDeclaringClass()).getName() + "." + method.getName();
    }

    /**
     * 获取指定类实现的所有接口，使用默认的类加载器
     *
     * @param clazz 要获取接口的类
     * @return 该类实现的所有接口的数组
     */
    public static Class<?>[] getAllInterfacesForClass(Class<?> clazz) {
        return getAllInterfacesForClass(clazz, null);
    }

    /**
     * 获取指定类实现的所有接口，可指定类加载器
     *
     * @param clazz       要获取接口的类
     * @param classLoader 类加载器，如果为 null 则使用默认逻辑
     * @return 该类实现的所有接口的数组
     */
    public static Class<?>[] getAllInterfacesForClass(Class<?> clazz, ClassLoader classLoader) {
        // 先获取类实现的所有接口的集合，再将集合转换为数组
        return toClassArray(getAllInterfacesForClassAsSet(clazz, classLoader));
    }

    /**
     * 获取指定类实现的所有接口的集合，可指定类加载器
     *
     * @param clazz       要获取接口的类
     * @param classLoader 类加载器，如果为 null 则使用默认逻辑
     * @return 该类实现的所有接口的集合
     */
    public static Set<Class<?>> getAllInterfacesForClassAsSet(Class<?> clazz, ClassLoader classLoader) {
        // 确保类对象不为 null
        Assert.notNull(clazz, "Class must not be null");
        // 如果 clazz 本身是接口且在指定类加载器下可见，则直接返回包含该接口的单元素集合
        if (clazz.isInterface() && isVisible(clazz, classLoader)) {
            return Collections.singleton(clazz);
        }
        // 用于存储类实现的所有接口的集合
        Set<Class<?>> interfaces = new LinkedHashSet<>();
        // 从当前类开始向上遍历继承层次
        Class<?> current = clazz;
        while (current != null) {
            // 获取当前类实现的所有接口
            Class<?>[] ifcs = current.getInterfaces();
            for (Class<?> ifc : ifcs) {
                // 如果接口在指定类加载器下可见，则添加到集合中
                if (isVisible(ifc, classLoader)) {
                    interfaces.add(ifc);
                }
            }
            // 向上获取父类
            current = current.getSuperclass();
        }
        return interfaces;
    }

    /**
     * 检查指定的类在给定的类加载器下是否可见
     *
     * @param clazz       要检查的类
     * @param classLoader 类加载器，如果为 null 则默认可见
     * @return 如果类可见返回 true，否则返回 false
     */
    public static boolean isVisible(Class<?> clazz, ClassLoader classLoader) {
        if (classLoader == null) {
            return true;
        }
        try {
            // 如果类的类加载器和指定的类加载器相同，则认为可见
            if (clazz.getClassLoader() == classLoader) {
                return true;
            }
        } catch (SecurityException ex) {
            // 捕获安全异常，继续进行可加载性检查
        }
        // 检查在指定类加载器下是否可以加载该类
        return isLoadable(clazz, classLoader);
    }

    /**
     * 检查指定的类在给定的类加载器下是否可以加载
     *
     * @param clazz       要检查的类
     * @param classLoader 类加载器
     * @return 如果可以加载返回 true，否则返回 false
     */
    private static boolean isLoadable(Class<?> clazz, ClassLoader classLoader) {
        try {
            // 尝试使用指定类加载器加载该类，如果加载的类和原类相同则认为可加载
            return (clazz == classLoader.loadClass(clazz.getName()));
        } catch (ClassNotFoundException ex) {
            // 没有找到对应的类，返回 false
            return false;
        }
    }

    /**
     * 将类的集合转换为类的数组
     *
     * @param collection 类的集合
     * @return 类的数组
     */
    public static Class<?>[] toClassArray(Collection<Class<?>> collection) {
        return collection.toArray(new Class<?>[0]);
    }

    /**
     * 检查指定的接口是否是 Java 语言核心接口
     *
     * @param ifc 要检查的接口
     * @return 如果是 Java 语言核心接口返回 true，否则返回 false
     */
    public static boolean isJavaLanguageInterface(Class<?> ifc) {
        return javaLanguageInterfaces.contains(ifc);
    }

    /**
     * 检查指定的类是否是内部类
     * 内部类是成员类且不是静态类
     *
     * @param clazz 要检查的类
     * @return 如果是内部类返回 true，否则返回 false
     */
    public static boolean isInnerClass(Class<?> clazz) {
        return (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers()));
    }

    /**
     * 检查一个类型是否可以赋值给另一个类型
     * 考虑了基本类型和包装类型的转换
     *
     * @param lhsType 左操作数类型
     * @param rhsType 右操作数类型
     * @return 如果可以赋值返回 true，否则返回 false
     */
    public static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
        // 确保左右操作数类型都不为 null
        Assert.notNull(lhsType, "Left-hand side type must not be null");
        Assert.notNull(rhsType, "Right-hand side type must not be null");
        // 直接使用 Java 的 isAssignableFrom 方法检查
        if (lhsType.isAssignableFrom(rhsType)) {
            return true;
        }
        if (lhsType.isPrimitive()) {
            // 如果左操作数是基本类型，查找右操作数对应的包装类型
            Class<?> resolvedPrimitive = primitiveWrapperTypeMap.get(rhsType);
            if (lhsType == resolvedPrimitive) {
                return true;
            }
        } else {
            // 如果左操作数不是基本类型，查找右操作数对应的基本类型的包装类型
            Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
            if (resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper)) {
                return true;
            }
        }
        return false;
    }
}
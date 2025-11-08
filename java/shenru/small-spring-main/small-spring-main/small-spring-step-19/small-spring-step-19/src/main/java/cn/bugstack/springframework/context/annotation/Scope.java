package cn.bugstack.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * 这是一个自定义注解 @Scope，用于指定 Bean 的作用域。
 * 在 Spring 框架中，Bean 的作用域定义了 Bean 实例的生命周期和可见范围。
 * 通过使用这个注解，可以方便地为 Bean 指定不同的作用域。
 */
@Target({ElementType.TYPE, ElementType.METHOD})
// 此注解可以应用于类和方法上。当应用于类上时，表示该类对应的 Bean 的作用域；
// 当应用于方法上时，通常用于配置方法返回的 Bean 的作用域。

@Retention(RetentionPolicy.RUNTIME)
// 注解的保留策略为运行时，这意味着在运行时可以通过反射机制获取到该注解的信息，
// 从而根据注解的值来配置 Bean 的作用域。

@Documented
// 表示该注解会被包含在 JavaDoc 中，方便开发人员查看和理解。

public @interface Scope {
    /**
     * 定义注解的属性 value，用于指定 Bean 的作用域。
     * 默认值为 "singleton"，表示单例作用域，即整个应用程序中只有一个该 Bean 的实例。
     *
     * @return Bean 的作用域字符串，常见的值有 "singleton"、"prototype" 等。
     */
    String value() default "singleton";
}
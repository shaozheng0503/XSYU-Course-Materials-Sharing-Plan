package cn.bugstack.springframework.core.annotation;

import cn.bugstack.springframework.core.util.ObjectUtils;
import cn.hutool.core.lang.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 抽象的支持别名的注解属性提取器，实现了 AnnotationAttributeExtractor 接口，用于从注解源中提取注解属性。
 * 它考虑了注解属性的别名情况，确保在使用别名时属性值的一致性。
 *
 * @author zhangdd on 2022/2/27
 */
abstract class AbstractAliasAwareAnnotationAttributeExtractor<S> implements AnnotationAttributeExtractor<S> {

    // 要合成的注解类型
    private final Class<? extends Annotation> annotationType;

    // 被注解的元素，可能为 null，如果不知道被注解的元素是什么
    private final Object annotatedElement;

    // 注解属性的底层来源，例如注解实例、注解属性的映射等
    private final S source;

    // 存储注解属性及其别名的映射关系，键为属性名，值为该属性的别名列表
    private final Map<String, List<String>> attributeAliasMap;

    /**
     * 构造一个新的 AbstractAliasAwareAnnotationAttributeExtractor 实例。
     *
     * @param annotationType     要合成的注解类型，不能为 null
     * @param annotatedElement   被注解的元素，如果不知道可以为 null
     * @param source             注解属性的底层来源，不能为 null
     */
    AbstractAliasAwareAnnotationAttributeExtractor(
            Class<? extends Annotation> annotationType, Object annotatedElement, S source) {

        // 确保注解类型不为 null
        Assert.notNull(annotationType, "annotationType must not be null");
        // 确保注解属性的底层来源不为 null
        Assert.notNull(source, "source must not be null");
        this.annotationType = annotationType;
        this.annotatedElement = annotatedElement;
        this.source = source;
        // 获取注解类型的属性别名映射
        this.attributeAliasMap = AnnotationUtils.getAttributeAliasMap(annotationType);
    }

    /**
     * 获取注解类型。
     *
     * @return 注解类型
     */
    @Override
    public final Class<? extends Annotation> getAnnotationType() {
        return this.annotationType;
    }

    /**
     * 获取被注解的元素。
     *
     * @return 被注解的元素
     */
    @Override
    public final Object getAnnotatedElement() {
        return this.annotatedElement;
    }

    /**
     * 获取注解属性的底层来源。
     *
     * @return 注解属性的底层来源
     */
    @Override
    public final S getSource() {
        return this.source;
    }

    /**
     * 获取指定注解属性方法对应的值，会处理属性别名的情况。
     *
     * @param attributeMethod 注解属性的方法
     * @return 属性值
     */
    @Override
    public final Object getAttributeValue(Method attributeMethod) {
        // 获取属性名
        String attributeName = attributeMethod.getName();
        // 从底层来源获取原始的属性值
        Object attributeValue = getRawAttributeValue(attributeMethod);

        // 获取该属性的别名列表
        List<String> aliasNames = this.attributeAliasMap.get(attributeName);
        if (aliasNames != null) {
            // 获取该属性的默认值
            Object defaultValue = AnnotationUtils.getDefaultValue(this.annotationType, attributeName);
            // 遍历别名列表
            for (String aliasName : aliasNames) {
                // 获取别名对应的原始属性值
                Object aliasValue = getRawAttributeValue(aliasName);

                // 检查属性值、别名值和默认值是否不一致，如果不一致则抛出异常
                if (!ObjectUtils.nullSafeEquals(attributeValue, aliasValue) &&
                        !ObjectUtils.nullSafeEquals(attributeValue, defaultValue) &&
                        !ObjectUtils.nullSafeEquals(aliasValue, defaultValue)) {
                    // 获取被注解元素的名称，如果不知道则显示 "unknown element"
                    String elementName = (this.annotatedElement != null ? this.annotatedElement.toString() : "unknown element");
                    throw new AnnotationConfigurationException(String.format(
                            "In annotation [%s] declared on %s and synthesized from [%s], attribute '%s' and its " +
                                    "alias '%s' are present with values of [%s] and [%s], but only one is permitted.",
                            this.annotationType.getName(), elementName, this.source, attributeName, aliasName,
                            ObjectUtils.nullSafeToString(attributeValue), ObjectUtils.nullSafeToString(aliasValue)));
                }

                // 如果属性值等于默认值，则使用别名的值
                if (ObjectUtils.nullSafeEquals(attributeValue, defaultValue)) {
                    attributeValue = aliasValue;
                }
            }
        }

        return attributeValue;
    }

    /**
     * 从底层来源获取与指定注解属性方法对应的原始、未修改的属性值。
     * 该方法由具体子类实现。
     *
     * @param attributeMethod 注解属性的方法
     * @return 原始属性值
     */
    protected abstract Object getRawAttributeValue(Method attributeMethod);

    /**
     * 从底层来源获取与指定注解属性名对应的原始、未修改的属性值。
     * 该方法由具体子类实现。
     *
     * @param attributeName 注解属性名
     * @return 原始属性值
     */
    protected abstract Object getRawAttributeValue(String attributeName);

}
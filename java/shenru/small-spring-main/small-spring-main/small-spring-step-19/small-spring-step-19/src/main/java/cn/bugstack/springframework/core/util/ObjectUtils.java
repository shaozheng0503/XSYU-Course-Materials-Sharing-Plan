package cn.bugstack.springframework.core.util;

import java.util.Arrays;

/**
 * 该工具类主要提供一些处理对象的通用方法，如判断数组是否为空、安全地将对象转换为字符串、
 * 安全地比较两个对象是否相等、安全地获取对象的哈希码等。
 *
 * @author zhangdd on 2022/2/26
 */
public class ObjectUtils {

    // 定义空字符串常量，用于后续判断和返回
    private static final String EMPTY_STRING = "";
    // 定义表示 null 的字符串常量，用于当对象为 null 时的返回值
    private static final String NULL_STRING = "null";

    /**
     * 判断一个对象数组是否为空。
     *
     * @param array 要判断的对象数组
     * @return 如果数组为 null 或者数组长度为 0，返回 true；否则返回 false
     */
    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 安全地将对象转换为字符串，处理了对象为 null 和数组的情况。
     *
     * @param obj 要转换为字符串的对象
     * @return 转换后的字符串，如果对象为 null 返回 "null"
     */
    public static String nullSafeToString(Object obj) {
        // 如果对象为 null，直接返回表示 null 的字符串
        if (obj == null) {
            return NULL_STRING;
        }
        // 如果对象本身就是字符串，直接返回该字符串
        if (obj instanceof String) {
            return (String) obj;
        }
        // 如果对象是对象数组，递归调用本方法处理数组
        if (obj instanceof Object[]) {
            return nullSafeToString((Object[]) obj);
        }
        // 如果对象是布尔数组，调用对应的处理方法
        if (obj instanceof boolean[]) {
            return nullSafeToString((boolean[]) obj);
        }
        // 如果对象是字节数组，调用对应的处理方法
        if (obj instanceof byte[]) {
            return nullSafeToString((byte[]) obj);
        }
        // 如果对象是字符数组，调用对应的处理方法
        if (obj instanceof char[]) {
            return nullSafeToString((char[]) obj);
        }
        // 如果对象是双精度浮点数数组，调用对应的处理方法
        if (obj instanceof double[]) {
            return nullSafeToString((double[]) obj);
        }
        // 如果对象是单精度浮点数数组，调用对应的处理方法
        if (obj instanceof float[]) {
            return nullSafeToString((float[]) obj);
        }
        // 如果对象是整数数组，调用对应的处理方法
        if (obj instanceof int[]) {
            return nullSafeToString((int[]) obj);
        }
        // 如果对象是长整数数组，调用对应的处理方法
        if (obj instanceof long[]) {
            return nullSafeToString((long[]) obj);
        }
        // 如果对象是短整数数组，调用对应的处理方法
        if (obj instanceof short[]) {
            return nullSafeToString((short[]) obj);
        }
        // 对于其他类型的对象，调用其 toString 方法并处理可能的 null 返回值
        String str = obj.toString();
        return (str != null ? str : EMPTY_STRING);
    }

    /**
     * 安全地比较两个对象是否相等，处理了对象为 null 和数组的情况。
     *
     * @param o1 第一个要比较的对象
     * @param o2 第二个要比较的对象
     * @return 如果两个对象相等返回 true，否则返回 false
     */
    public static boolean nullSafeEquals(Object o1, Object o2) {
        // 如果两个对象引用相同，直接返回 true
        if (o1 == o2) {
            return true;
        }
        // 如果其中一个对象为 null，另一个不为 null，返回 false
        if (o1 == null || o2 == null) {
            return false;
        }
        // 调用对象的 equals 方法进行比较
        if (o1.equals(o2)) {
            return true;
        }
        // 如果两个对象都是数组，调用数组比较方法
        if (o1.getClass().isArray() && o2.getClass().isArray()) {
            return arrayEquals(o1, o2);
        }
        // 其他情况返回 false
        return false;
    }

    /**
     * 比较两个数组是否相等，处理了不同类型的数组。
     *
     * @param o1 第一个要比较的数组对象
     * @param o2 第二个要比较的数组对象
     * @return 如果两个数组相等返回 true，否则返回 false
     */
    private static boolean arrayEquals(Object o1, Object o2) {
        // 如果是对象数组，使用 Arrays 工具类的 equals 方法比较
        if (o1 instanceof Object[] && o2 instanceof Object[]) {
            return Arrays.equals((Object[]) o1, (Object[]) o2);
        }
        // 如果是布尔数组，使用 Arrays 工具类的 equals 方法比较
        if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
            return Arrays.equals((boolean[]) o1, (boolean[]) o2);
        }
        // 如果是字节数组，使用 Arrays 工具类的 equals 方法比较
        if (o1 instanceof byte[] && o2 instanceof byte[]) {
            return Arrays.equals((byte[]) o1, (byte[]) o2);
        }
        // 如果是字符数组，使用 Arrays 工具类的 equals 方法比较
        if (o1 instanceof char[] && o2 instanceof char[]) {
            return Arrays.equals((char[]) o1, (char[]) o2);
        }
        // 如果是双精度浮点数数组，使用 Arrays 工具类的 equals 方法比较
        if (o1 instanceof double[] && o2 instanceof double[]) {
            return Arrays.equals((double[]) o1, (double[]) o2);
        }
        // 如果是单精度浮点数数组，使用 Arrays 工具类的 equals 方法比较
        if (o1 instanceof float[] && o2 instanceof float[]) {
            return Arrays.equals((float[]) o1, (float[]) o2);
        }
        // 如果是整数数组，使用 Arrays 工具类的 equals 方法比较
        if (o1 instanceof int[] && o2 instanceof int[]) {
            return Arrays.equals((int[]) o1, (int[]) o2);
        }
        // 如果是长整数数组，使用 Arrays 工具类的 equals 方法比较
        if (o1 instanceof long[] && o2 instanceof long[]) {
            return Arrays.equals((long[]) o1, (long[]) o2);
        }
        // 如果是短整数数组，使用 Arrays 工具类的 equals 方法比较
        if (o1 instanceof short[] && o2 instanceof short[]) {
            return Arrays.equals((short[]) o1, (short[]) o2);
        }
        // 其他情况返回 false
        return false;
    }

    /**
     * 安全地获取对象的哈希码，处理了对象为 null 和数组的情况。
     *
     * @param obj 要获取哈希码的对象
     * @return 对象的哈希码，如果对象为 null 返回 0
     */
    public static int nullSafeHashCode(Object obj) {
        // 如果对象为 null，返回 0
        if (obj == null) {
            return 0;
        }
        // 如果对象是数组
        if (obj.getClass().isArray()) {
            // 如果是对象数组，递归调用本方法处理数组
            if (obj instanceof Object[]) {
                return nullSafeHashCode((Object[]) obj);
            }
            // 如果是布尔数组，调用对应的处理方法
            if (obj instanceof boolean[]) {
                return nullSafeHashCode((boolean[]) obj);
            }
            // 如果是字节数组，调用对应的处理方法
            if (obj instanceof byte[]) {
                return nullSafeHashCode((byte[]) obj);
            }
            // 如果是字符数组，调用对应的处理方法
            if (obj instanceof char[]) {
                return nullSafeHashCode((char[]) obj);
            }
            // 如果是双精度浮点数数组，调用对应的处理方法
            if (obj instanceof double[]) {
                return nullSafeHashCode((double[]) obj);
            }
            // 如果是单精度浮点数数组，调用对应的处理方法
            if (obj instanceof float[]) {
                return nullSafeHashCode((float[]) obj);
            }
            // 如果是整数数组，调用对应的处理方法
            if (obj instanceof int[]) {
                return nullSafeHashCode((int[]) obj);
            }
            // 如果是长整数数组，调用对应的处理方法
            if (obj instanceof long[]) {
                return nullSafeHashCode((long[]) obj);
            }
            // 如果是短整数数组，调用对应的处理方法
            if (obj instanceof short[]) {
                return nullSafeHashCode((short[]) obj);
            }
        }
        // 对于其他类型的对象，调用其 hashCode 方法
        return obj.hashCode();
    }
}
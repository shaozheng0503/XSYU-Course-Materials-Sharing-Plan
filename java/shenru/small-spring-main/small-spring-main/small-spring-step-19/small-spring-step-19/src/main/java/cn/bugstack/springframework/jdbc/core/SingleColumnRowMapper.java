package cn.bugstack.springframework.jdbc.core;

import cn.bugstack.springframework.jdbc.IncorrectResultSetColumnCountException;
import cn.bugstack.springframework.jdbc.support.JdbcUtils;
import cn.bugstack.springframework.util.NumberUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * SingleColumnRowMapper 类实现了 RowMapper 接口，用于将结果集中的单列表数据映射为指定类型的对象。
 * 该类会检查结果集是否只包含一列数据，如果不是则抛出异常。同时，它还支持将获取到的值转换为所需的类型。
 *
 * @author zhangdd on 2022/2/10
 */
public class SingleColumnRowMapper<T> implements RowMapper<T> {

    // 所需的目标类型，用于将结果集中的值转换为该类型
    private Class<?> requireType;

    /**
     * 无参构造函数，不指定目标类型
     */
    public SingleColumnRowMapper() {
    }

    /**
     * 有参构造函数，指定所需的目标类型
     *
     * @param requireType 所需的目标类型
     */
    public SingleColumnRowMapper(Class<T> requireType) {
        this.requireType = requireType;
    }

    /**
     * 将结果集中的一行数据映射为指定类型的对象
     *
     * @param rs     结果集对象
     * @param rowNum 当前行号
     * @return 映射后的对象
     * @throws SQLException 如果在处理结果集时发生 SQL 异常
     */
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        // 获取结果集的元数据，用于获取列数等信息
        ResultSetMetaData rsMetaData = rs.getMetaData();
        // 获取结果集中的列数
        int columnCount = rsMetaData.getColumnCount();
        // 检查结果集是否只包含一列数据，如果不是则抛出异常
        if (columnCount != 1) {
            throw new IncorrectResultSetColumnCountException(1, columnCount);
        }
        // 从结果集中获取指定列的值，并尝试转换为所需的类型
        Object result = getColumnValue(rs, 1, this.requireType);
        // 检查获取到的结果是否不为空，且指定了目标类型，并且结果的类型与目标类型不匹配
        if (result != null && this.requireType != null && !this.requireType.isInstance(result)) {
            // 尝试将结果转换为所需的类型
            try {
                return (T) convertValueToRequiredType(result, this.requireType);
            } catch (IllegalArgumentException ex) {
                // 转换失败，这里可以根据具体需求进行异常处理，当前只是捕获未做进一步处理
            }
        }
        // 返回映射后的对象
        return (T) result;
    }

    /**
     * 从结果集中获取指定列的值，并根据是否指定目标类型进行不同的处理
     *
     * @param rs          结果集对象
     * @param index       列索引
     * @param requireType 所需的目标类型
     * @return 列的值
     * @throws SQLException 如果在获取列值时发生 SQL 异常
     */
    protected Object getColumnValue(ResultSet rs, int index, Class<?> requireType) throws SQLException {
        if (null != requireType) {
            // 如果指定了目标类型，使用 JdbcUtils 工具类获取指定类型的列值
            return JdbcUtils.getResultSetValue(rs, index, requireType);
        } else {
            // 如果未指定目标类型，调用另一个 getColumnValue 方法获取列值
            return getColumnValue(rs, index);
        }
    }

    /**
     * 从结果集中获取指定列的值
     *
     * @param rs    结果集对象
     * @param index 列索引
     * @return 列的值
     * @throws SQLException 如果在获取列值时发生 SQL 异常
     */
    protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
        // 使用 JdbcUtils 工具类获取列值
        return JdbcUtils.getResultSetValue(rs, index);
    }

    /**
     * 将值转换为所需的目标类型
     *
     * @param value        要转换的值
     * @param requiredType 所需的目标类型
     * @return 转换后的值
     */
    protected Object convertValueToRequiredType(Object value, Class<?> requiredType) {
        if (String.class == requiredType) {
            // 如果目标类型是 String，直接将值转换为字符串
            return value.toString();
        } else if (Number.class.isAssignableFrom(requiredType)) {
            if (value instanceof Number) {
                // 如果值本身是 Number 类型，将其转换为目标 Number 类型
                return NumberUtils.convertNumberToTargetClass(((Number) value), (Class<Number>) requiredType);
            } else {
                // 如果值不是 Number 类型，将其字符串表示形式解析为目标 Number 类型
                return NumberUtils.parseNumber(value.toString(), (Class<Number>) requiredType);
            }
        }
        // 这里暂时不添加 spring - core 里的类型转换处理
        // else if (this.conversionService != null && this.conversionService.canConvert(value.getClass(), requiredType)) {
        //     return this.conversionService.convert(value, requiredType);
        // }
        else {
            // 如果无法进行转换，抛出 IllegalArgumentException 异常
            throw new IllegalArgumentException(
                    "Value [" + value + "] is of type [" + value.getClass().getName() +
                            "] and cannot be converted to required type [" + requiredType.getName() + "]");
        }
    }
}
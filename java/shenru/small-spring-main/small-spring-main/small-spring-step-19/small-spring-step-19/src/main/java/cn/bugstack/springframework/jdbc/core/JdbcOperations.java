package cn.bugstack.springframework.jdbc.core;

import java.util.List;
import java.util.Map;

/**
 * JdbcOperations 接口定义了一系列用于执行 JDBC（Java Database Connectivity）操作的方法。
 * 这些方法涵盖了执行 SQL 语句、查询数据库并处理结果集等常见的数据库操作，为上层应用提供了统一的数据库操作抽象，
 * 使得应用可以方便地使用不同的数据库实现进行数据交互，而无需关心底层的具体实现细节。
 *
 * @author zhangdd on 2022/2/9
 */
public interface JdbcOperations {

    /**
     * 执行一个 StatementCallback 回调接口定义的操作。
     * 该方法允许用户自定义对 Statement 对象的操作逻辑，例如执行复杂的 SQL 语句或进行特定的数据库交互。
     *
     * @param action 实现了 StatementCallback 接口的回调对象，其中定义了具体的操作逻辑
     * @param <T>    操作结果的泛型类型
     * @return 操作执行后的结果，类型由泛型 T 指定
     */
    <T> T execute(StatementCallback<T> action);

    /**
     * 直接执行一条 SQL 语句。
     * 通常用于执行不需要返回结果的 SQL 语句，如 INSERT、UPDATE、DELETE 等。
     *
     * @param sql 要执行的 SQL 语句
     */
    void execute(String sql);

    //---------------------------------------------------------------------
    // query
    //---------------------------------------------------------------------

    /**
     * 执行 SQL 查询语句，并使用 ResultSetExtractor 对查询结果集进行处理。
     * ResultSetExtractor 允许用户自定义对结果集的提取和转换逻辑，以得到特定类型的结果。
     *
     * @param sql  要执行的 SQL 查询语句
     * @param res  实现了 ResultSetExtractor 接口的对象，用于处理查询结果集
     * @param <T>  处理结果的泛型类型
     * @return 经过 ResultSetExtractor 处理后的结果，类型由泛型 T 指定
     */
    <T> T query(String sql, ResultSetExtractor<T> res);

    /**
     * 执行带有参数的 SQL 查询语句，并使用 ResultSetExtractor 对查询结果集进行处理。
     *
     * @param sql  要执行的 SQL 查询语句，其中可能包含占位符（如 ?）
     * @param args SQL 语句中的参数数组，用于替换占位符
     * @param rse  实现了 ResultSetExtractor 接口的对象，用于处理查询结果集
     * @param <T>  处理结果的泛型类型
     * @return 经过 ResultSetExtractor 处理后的结果，类型由泛型 T 指定
     */
    <T> T query(String sql, Object[] args, ResultSetExtractor<T> rse);

    /**
     * 执行 SQL 查询语句，并使用 RowMapper 将结果集中的每一行映射为一个对象，最终返回对象列表。
     * RowMapper 用于定义如何将结果集中的一行数据映射为一个 Java 对象。
     *
     * @param sql       要执行的 SQL 查询语句
     * @param rowMapper 实现了 RowMapper 接口的对象，用于将结果集的每一行映射为一个对象
     * @param <T>       映射对象的泛型类型
     * @return 包含映射后对象的列表
     */
    <T> List<T> query(String sql, RowMapper<T> rowMapper);

    /**
     * 执行带有参数的 SQL 查询语句，并使用 RowMapper 将结果集中的每一行映射为一个对象，最终返回对象列表。
     *
     * @param sql       要执行的 SQL 查询语句，其中可能包含占位符
     * @param args      SQL 语句中的参数数组，用于替换占位符
     * @param rowMapper 实现了 RowMapper 接口的对象，用于将结果集的每一行映射为一个对象
     * @param <T>       映射对象的泛型类型
     * @return 包含映射后对象的列表
     */
    <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper);

    /**
     * 使用 PreparedStatementSetter 设置 SQL 查询语句的参数，执行查询，并使用 ResultSetExtractor 处理结果集。
     *
     * @param sql  要执行的 SQL 查询语句
     * @param pss  实现了 PreparedStatementSetter 接口的对象，用于设置 SQL 语句的参数
     * @param rse  实现了 ResultSetExtractor 接口的对象，用于处理查询结果集
     * @param <T>  处理结果的泛型类型
     * @return 经过 ResultSetExtractor 处理后的结果，类型由泛型 T 指定
     */
    <T> T query(String sql, PreparedStatementSetter pss, ResultSetExtractor<T> rse);

    //---------------------------------------------------------------------
    // queryForList
    //---------------------------------------------------------------------

    /**
     * 执行 SQL 查询语句，将结果集中的每一行转换为一个 Map 对象，最终返回包含这些 Map 对象的列表。
     * 每个 Map 对象的键为列名，值为对应列的数据。
     *
     * @param sql 要执行的 SQL 查询语句
     * @return 包含结果集每行数据的 Map 对象列表
     */
    List<Map<String, Object>> queryForList(String sql);

    /**
     * 查询数据库表中某一个字段，将查询结果转换为指定类型的对象列表。
     *
     * @param sql         要执行的 SQL 查询语句
     * @param elementType 列表中元素的类型
     * @param <T>         列表元素的泛型类型
     * @return 包含指定类型对象的列表
     */
    <T> List<T> queryForList(String sql, Class<T> elementType);

    /**
     * 执行带有参数的 SQL 查询语句，查询数据库表中某一个字段，将查询结果转换为指定类型的对象列表。
     *
     * @param sql         要执行的 SQL 查询语句，其中可能包含占位符
     * @param elementType 列表中元素的类型
     * @param args        SQL 语句中的参数数组，用于替换占位符
     * @param <T>         列表元素的泛型类型
     * @return 包含指定类型对象的列表
     */
    <T> List<T> queryForList(String sql, Class<T> elementType, Object... args);

    /**
     * 执行带有参数的 SQL 查询语句，将结果集中的每一行转换为一个 Map 对象，最终返回包含这些 Map 对象的列表。
     *
     * @param sql  要执行的 SQL 查询语句，其中可能包含占位符
     * @param args SQL 语句中的参数数组，用于替换占位符
     * @return 包含结果集每行数据的 Map 对象列表
     */
    List<Map<String, Object>> queryForList(String sql, Object... args);

    //---------------------------------------------------------------------
    // queryForObject
    //---------------------------------------------------------------------

    /**
     * 执行 SQL 查询语句，使用 RowMapper 将结果集中的第一行映射为一个对象并返回。
     *
     * @param sql       要执行的 SQL 查询语句
     * @param rowMapper 实现了 RowMapper 接口的对象，用于将结果集的第一行映射为一个对象
     * @param <T>       映射对象的泛型类型
     * @return 映射后的对象
     */
    <T> T queryForObject(String sql, RowMapper<T> rowMapper);

    /**
     * 执行带有参数的 SQL 查询语句，使用 RowMapper 将结果集中的第一行映射为一个对象并返回。
     *
     * @param sql       要执行的 SQL 查询语句，其中可能包含占位符
     * @param args      SQL 语句中的参数数组，用于替换占位符
     * @param rowMapper 实现了 RowMapper 接口的对象，用于将结果集的第一行映射为一个对象
     * @param <T>       映射对象的泛型类型
     * @return 映射后的对象
     */
    <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper);

    /**
     * 查询数据库表中某一条记录的某一个字段，并将结果转换为指定类型的对象。
     *
     * @param sql          要执行的 SQL 查询语句
     * @param requiredType 结果对象的类型
     * @param <T>          结果对象的泛型类型
     * @return 查询结果转换后的对象
     */
    <T> T queryForObject(String sql, Class<T> requiredType);

    //---------------------------------------------------------------------
    // queryForMap
    //---------------------------------------------------------------------

    /**
     * 执行 SQL 查询语句，将结果集中的第一行转换为一个 Map 对象并返回。
     * Map 对象的键为列名，值为对应列的数据。
     *
     * @param sql 要执行的 SQL 查询语句
     * @return 包含结果集第一行数据的 Map 对象
     */
    Map<String, Object> queryForMap(String sql);

    /**
     * 执行带有参数的 SQL 查询语句，将结果集中的第一行转换为一个 Map 对象并返回。
     *
     * @param sql  要执行的 SQL 查询语句，其中可能包含占位符
     * @param args SQL 语句中的参数数组，用于替换占位符
     * @return 包含结果集第一行数据的 Map 对象
     */
    Map<String, Object> queryForMap(String sql, Object... args);

}
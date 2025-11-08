package cn.bugstack.springframework.jdbc.core;

import cn.bugstack.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 该类实现了 RowMapper 接口，主要功能是将 SQL 查询结果集中的每一行数据逐列获取，并存储到一个 Map 中。
 * 其中，Map 的键为列名，值为该列对应的数据值。这样做可以方便地将数据库查询结果以键值对的形式进行处理和使用。
 *
 * @author zhangdd on 2022/2/10
 */
public class ColumnMapRowMapper implements RowMapper<Map<String, Object>> {

    /**
     * 将结果集（ResultSet）中的一行数据映射为一个 Map 对象。
     *
     * @param rs     包含查询结果的 ResultSet 对象
     * @param rowNum 当前处理的行号（从 0 开始）
     * @return 包含当前行各列数据的 Map 对象，键为列名，值为列对应的数据值
     * @throws SQLException 如果在处理结果集时发生 SQL 相关异常
     */
    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        // 获取结果集的元数据，元数据包含了结果集的结构信息，如列数、列名等
        ResultSetMetaData rsMetaData = rs.getMetaData();
        // 获取结果集中的列数
        int columnCount = rsMetaData.getColumnCount();
        // 创建一个用于存储当前行数据的 Map 对象，初始容量根据列数确定
        Map<String, Object> mapOfColumnValues = createColumnMap(columnCount);

        // 遍历结果集中的每一列
        for (int i = 1; i <= columnCount; i++) {
            // 通过 JdbcUtils 工具类根据列索引获取列名
            String columnName = JdbcUtils.lookupColumnName(rsMetaData, i);
            // 将列名处理后作为键，获取该列对应的数据值作为值，放入 Map 中
            // putIfAbsent 方法表示如果键不存在才进行插入操作
            mapOfColumnValues.putIfAbsent(getColumnKey(columnName), getColumnValue(rs, i));
        }

        // 返回包含当前行数据的 Map 对象
        return mapOfColumnValues;
    }

    /**
     * 创建一个用于存储列数据的 Map 对象。
     *
     * @param columnCount 结果集中的列数，用于确定 Map 的初始容量
     * @return 一个初始容量为 columnCount 的 LinkedHashMap 对象
     */
    protected Map<String, Object> createColumnMap(int columnCount) {
        // 使用 LinkedHashMap 可以保证插入元素的顺序，方便后续按顺序处理数据
        return new LinkedHashMap<>(columnCount);
    }

    /**
     * 获取用于存储在 Map 中的列键。
     * 这里默认直接使用列名作为键，子类可以重写该方法进行自定义处理。
     *
     * @param columnName 原始列名
     * @return 用于存储在 Map 中的列键
     */
    protected String getColumnKey(String columnName) {
        return columnName;
    }

    /**
     * 从结果集中获取指定列索引对应的数据值。
     *
     * @param rs    包含查询结果的 ResultSet 对象
     * @param index 列索引（从 1 开始）
     * @return 该列对应的数据值
     * @throws SQLException 如果在获取数据值时发生 SQL 相关异常
     */
    protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
        // 使用 JdbcUtils 工具类从结果集中获取指定列索引的数据值
        return JdbcUtils.getResultSetValue(rs, index);
    }
}
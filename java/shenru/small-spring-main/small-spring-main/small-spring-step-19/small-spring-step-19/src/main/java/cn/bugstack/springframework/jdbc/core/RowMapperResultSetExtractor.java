package cn.bugstack.springframework.jdbc.core;

import cn.hutool.core.lang.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 将查询结果的ResultSet逐行的进行转换提取，转换成map，放到list里
 *
 * @author zhangdd on 2022/2/10
 */
public class RowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    // 用于将结果集的每一行映射为特定类型对象的 RowMapper 对象
    private final RowMapper<T> rowMapper;

    // 预期的结果集行数，可用于预先分配列表的初始容量，提高性能
    private final int rowsExpected;

    /**
     * 构造函数，初始化 RowMapper，默认预期行数为 0
     *
     * @param rowMapper 用于映射结果集行的 RowMapper 对象，不能为 null
     */
    public RowMapperResultSetExtractor(RowMapper<T> rowMapper) {
        // 调用另一个构造函数，传入默认的预期行数 0
        this(rowMapper, 0);
    }

    /**
     * 构造函数，初始化 RowMapper 和预期的结果集行数
     *
     * @param rowMapper    用于映射结果集行的 RowMapper 对象，不能为 null
     * @param rowsExpected 预期的结果集行数
     */
    public RowMapperResultSetExtractor(RowMapper<T> rowMapper, int rowsExpected) {
        // 确保传入的 RowMapper 不为 null，如果为 null 则抛出异常
        Assert.notNull(rowMapper, "RowMapper is required");

        this.rowMapper = rowMapper;
        this.rowsExpected = rowsExpected;
    }

    /**
     * 从结果集中提取数据，并将每行数据通过 RowMapper 转换为指定类型的对象，最终存储在列表中返回
     *
     * @param rs 包含查询结果的 ResultSet 对象
     * @return 包含转换后对象的列表
     * @throws SQLException 如果在处理结果集时发生 SQL 异常
     */
    @Override
    public List<T> extractData(ResultSet rs) throws SQLException {
        // 根据预期行数来初始化列表，如果预期行数大于 0，则使用预期行数作为初始容量创建列表，
        // 否则创建一个默认初始容量的列表，这样可以减少列表扩容的开销
        List<T> results = this.rowsExpected > 0 ? new ArrayList<>(this.rowsExpected) : new ArrayList<>();
        // 记录当前处理的行号，从 0 开始
        int rowNum = 0;
        // 遍历结果集的每一行
        while (rs.next()) {
            // 使用 RowMapper 将当前行的数据映射为指定类型的对象，并添加到结果列表中
            // 每处理一行，行号加 1
            results.add(this.rowMapper.mapRow(rs, rowNum++));
        }
        // 返回包含所有转换后对象的列表
        return results;
    }
}
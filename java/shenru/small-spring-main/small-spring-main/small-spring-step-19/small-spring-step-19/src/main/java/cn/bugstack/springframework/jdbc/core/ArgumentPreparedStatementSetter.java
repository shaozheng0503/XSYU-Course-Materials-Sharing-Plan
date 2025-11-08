package cn.bugstack.springframework.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 这个类实现了 PreparedStatementSetter 接口，主要用于将一组参数设置到 PreparedStatement 对象中。
 * 当我们使用预编译 SQL 语句（PreparedStatement）时，需要为 SQL 语句中的占位符（?）设置具体的值，
 * 该类就负责完成这个参数设置的任务。
 *
 * @author zhangdd on 2022/2/12
 */
public class ArgumentPreparedStatementSetter implements PreparedStatementSetter {

    // 存储要设置到 PreparedStatement 中的参数数组
    private final Object[] args;

    /**
     * 构造函数，接收一个参数数组，用于后续将这些参数设置到 PreparedStatement 中。
     *
     * @param args 要设置的参数数组
     */
    public ArgumentPreparedStatementSetter(Object[] args) {
        this.args = args;
    }

    /**
     * 实现 PreparedStatementSetter 接口的 setValues 方法，该方法会将参数数组中的值依次设置到 PreparedStatement 对象的占位符中。
     *
     * @param ps 预编译的 SQL 语句对象，用于执行 SQL 操作
     * @throws SQLException 如果在设置参数过程中发生 SQL 相关的异常
     */
    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
        // 检查参数数组是否不为空
        if (null != args) {
            // 遍历参数数组，从索引 1 开始是因为 PreparedStatement 的参数索引是从 1 开始的
            for (int i = 1; i <= args.length; i++) {
                // 将参数数组中的值设置到 PreparedStatement 的对应占位符位置
                // args[i - 1] 是因为 Java 数组索引从 0 开始，而 PreparedStatement 参数索引从 1 开始
                ps.setObject(i, args[i - 1]);
            }
        }
    }
}
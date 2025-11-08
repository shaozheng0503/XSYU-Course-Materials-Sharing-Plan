package cn.bugstack.springframework.jdbc.datasource;

import cn.bugstack.springframework.jdbc.CannotGetJdbcConnectionException;
import cn.bugstack.springframework.tx.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSourceUtils 类提供了一系列用于管理 JDBC 连接的工具方法。
 * 这些方法主要用于从数据源获取连接、释放连接以及关闭连接等操作，
 * 并且在获取连接时会考虑事务同步管理器中是否已经存在可用的连接。
 *
 * @author zhangdd on 2022/2/9
 */
public abstract class DataSourceUtils {

    /**
     * 从指定的数据源获取一个 JDBC 连接。
     * 该方法会调用 doGetConnection 方法尝试获取连接，
     * 如果在获取过程中出现 SQLException，则会抛出 CannotGetJdbcConnectionException 异常。
     *
     * @param dataSource 数据源对象，用于获取数据库连接
     * @return 从数据源获取的 JDBC 连接
     * @throws CannotGetJdbcConnectionException 如果无法从数据源获取连接
     */
    public static Connection getConnection(DataSource dataSource) {
        try {
            // 调用 doGetConnection 方法尝试获取连接
            return doGetConnection(dataSource);
        } catch (SQLException e) {
            // 若出现 SQL 异常，抛出 CannotGetJdbcConnectionException 异常
            throw new CannotGetJdbcConnectionException("Failed to obtain JDBC Connection", e);
        }
    }

    /**
     * 实际执行从数据源获取 JDBC 连接的操作。
     * 首先会从事务同步管理器中查找是否已经存在与该数据源关联的连接持有者，
     * 如果存在且连接持有者中有可用的连接，则直接返回该连接；
     * 否则，调用 fetchConnection 方法从数据源获取新的连接。
     *
     * @param dataSource 数据源对象
     * @return 从数据源获取的 JDBC 连接
     * @throws SQLException 如果在获取连接过程中出现 SQL 异常
     */
    public static Connection doGetConnection(DataSource dataSource) throws SQLException {
        // 从事务同步管理器中获取与数据源关联的连接持有者
        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
        // 检查连接持有者是否存在且其中有可用的连接
        if (null != conHolder && conHolder.hasConnection()) {
            // 若存在，则直接返回连接持有者中的连接
            return conHolder.getConnection();
        }
        // 若不存在，则从数据源获取新的连接
        return fetchConnection(dataSource);
    }

    /**
     * 从数据源中获取一个新的 JDBC 连接。
     * 调用数据源的 getConnection 方法获取连接，
     * 如果获取的连接为 null，则抛出 IllegalArgumentException 异常。
     *
     * @param dataSource 数据源对象
     * @return 从数据源获取的新的 JDBC 连接
     * @throws SQLException 如果在获取连接过程中出现 SQL 异常
     */
    private static Connection fetchConnection(DataSource dataSource) throws SQLException {
        // 调用数据源的 getConnection 方法获取连接
        Connection conn = dataSource.getConnection();
        // 检查获取的连接是否为 null
        if (null == conn) {
            // 若为 null，则抛出异常
            throw new IllegalArgumentException("DataSource return null from getConnection():" + dataSource);
        }
        return conn;
    }

    /**
     * 释放指定的 JDBC 连接。
     * 该方法会调用 doReleaseConnection 方法来实际执行释放操作，
     * 并捕获可能出现的 SQLException 和其他异常，在出现异常时进行日志记录（此处注释掉了日志记录代码）。
     *
     * @param con        要释放的 JDBC 连接
     * @param dataSource 数据源对象
     */
    public static void releaseConnection(Connection con, DataSource dataSource) {
        try {
            // 调用 doReleaseConnection 方法释放连接
            doReleaseConnection(con, dataSource);
        } catch (SQLException ex) {
            // 捕获 SQL 异常，此处可添加日志记录
//            logger.debug("Could not close JDBC Connection", ex);
        } catch (Throwable ex) {
            // 捕获其他异常，此处可添加日志记录
//            logger.debug("Unexpected exception on closing JDBC Connection", ex);
        }
    }

    /**
     * 实际执行释放 JDBC 连接的操作。
     * 首先检查连接是否为 null，如果为 null 则直接返回；
     * 否则，调用 doCloseConnection 方法关闭连接。
     *
     * @param con        要释放的 JDBC 连接
     * @param dataSource 数据源对象
     * @throws SQLException 如果在释放连接过程中出现 SQL 异常
     */
    public static void doReleaseConnection(Connection con, DataSource dataSource) throws SQLException {
        // 检查连接是否为 null
        if (con == null) {
            return;
        }
        // 调用 doCloseConnection 方法关闭连接
        doCloseConnection(con, dataSource);
    }

    /**
     * 关闭指定的 JDBC 连接。
     * 调用连接的 close 方法来关闭连接。
     *
     * @param con        要关闭的 JDBC 连接
     * @param dataSource 数据源对象
     * @throws SQLException 如果在关闭连接过程中出现 SQL 异常
     */
    public static void doCloseConnection(Connection con, DataSource dataSource) throws SQLException {
        // 调用连接的 close 方法关闭连接
        con.close();
    }
}
package cn.bugstack.springframework.jdbc.datasource;

import cn.bugstack.springframework.beans.factory.InitializingBean;
import cn.bugstack.springframework.tx.transaction.CannotCreateTransactionException;
import cn.bugstack.springframework.tx.transaction.TransactionDefinition;
import cn.bugstack.springframework.tx.transaction.TransactionException;
import cn.bugstack.springframework.tx.transaction.support.AbstractPlatformTransactionManager;
import cn.bugstack.springframework.tx.transaction.support.DefaultTransactionStatus;
import cn.bugstack.springframework.tx.transaction.support.TransactionSynchronizationManager;
import cn.hutool.core.lang.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DataSourceTransactionManager 类用于管理基于数据源（DataSource）的事务。
 * 它继承自 AbstractPlatformTransactionManager 类，实现了 InitializingBean 接口，
 * 可以在属性设置完成后进行初始化检查，并提供了事务的获取、开始、提交和回滚等操作。
 *
 * @author zhangdd on 2022/2/24
 */
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager
        implements InitializingBean {

    // 数据源对象，用于获取数据库连接
    private DataSource dataSource;

    /**
     * 无参构造函数
     */
    public DataSourceTransactionManager() {

    }

    /**
     * 有参构造函数，接收一个数据源对象并进行初始化设置
     *
     * @param dataSource 数据源对象
     */
    public DataSourceTransactionManager(DataSource dataSource) {
        // 设置数据源
        setDataSource(dataSource);
        // 调用初始化方法进行属性检查
        afterPropertiesSet();
    }

    /**
     * 设置数据源
     *
     * @param dataSource 数据源对象
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据源
     *
     * @return 数据源对象
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取数据源，如果数据源为空则抛出异常
     *
     * @return 数据源对象
     */
    protected DataSource obtainDataSource() {
        DataSource dataSource = getDataSource();
        // 确保数据源不为空
        Assert.notNull(dataSource, "No DataSource set");
        return dataSource;
    }

    /**
     * 获取当前事务对象
     *
     * @return 事务对象
     * @throws TransactionException 如果获取事务过程中出现异常
     */
    @Override
    protected Object doGetTransaction() throws TransactionException {
        // 创建一个数据源事务对象
        DataSourceTransactionObject txObject = new DataSourceTransactionObject();
        // 从事务同步管理器中获取当前数据源对应的连接持有者
        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(obtainDataSource());
        // 设置连接持有者到事务对象中
        txObject.setConnectionHolder(conHolder, false);
        return txObject;
    }

    /**
     * 提交事务
     *
     * @param status 事务状态对象
     * @throws TransactionException 如果提交事务过程中出现异常
     */
    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        // 从事务状态中获取数据源事务对象
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        // 获取连接持有者中的数据库连接
        Connection con = txObject.getConnectionHolder().getConnection();
        try {
            // 提交数据库连接上的事务
            con.commit();
        } catch (SQLException e) {
            // 捕获 SQL 异常并抛出事务异常
            throw new TransactionException("Could not commit JDBC transaction", e);
        }
    }

    /**
     * 回滚事务
     *
     * @param status 事务状态对象
     * @throws TransactionException 如果回滚事务过程中出现异常
     */
    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        // 从事务状态中获取数据源事务对象
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        // 获取连接持有者中的数据库连接
        Connection con = txObject.getConnectionHolder().getConnection();
        try {
            // 回滚数据库连接上的事务
            con.rollback();
        } catch (SQLException e) {
            // 捕获 SQL 异常并抛出事务异常
            throw new TransactionException("Could not roll back JDBC transaction", e);
        }
    }

    /**
     * 开始一个新的事务
     *
     * @param transaction 事务对象
     * @param definition  事务定义，包含事务的属性，如隔离级别、传播行为等
     * @throws TransactionException 如果开始事务过程中出现异常
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        // 将事务对象转换为数据源事务对象
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;
        Connection con = null;
        try {
            // 从数据源获取一个新的数据库连接
            Connection newCon = obtainDataSource().getConnection();
            // 创建一个新的连接持有者并设置到事务对象中
            txObject.setConnectionHolder(new ConnectionHolder(newCon), true);

            // 获取事务对象中的数据库连接
            con = txObject.getConnectionHolder().getConnection();
            // 如果连接的自动提交模式为开启，则将其关闭
            if (con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            // 根据事务定义准备事务连接
            prepareTransactionalConnection(con, definition);

            // 将连接持有者绑定到事务同步管理器中，以便后续使用
            TransactionSynchronizationManager.bindResource(obtainDataSource(), txObject.getConnectionHolder());

        } catch (SQLException e) {
            try {
                // 如果出现异常，尝试关闭数据库连接
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // 将事务对象中的连接持有者置为空
            txObject.setConnectionHolder(null, false);
            // 抛出无法创建事务的异常
            throw new CannotCreateTransactionException("Could not open JDBC Connection for transaction", e);
        }
    }

    /**
     * 在属性设置完成后进行初始化检查，如果数据源为空则抛出异常
     */
    @Override
    public void afterPropertiesSet() {
        if (null == getDataSource()) {
            throw new IllegalArgumentException("Property 'datasource' is required");
        }
    }

    /**
     * 根据事务定义准备事务连接
     *
     * @param con        数据库连接
     * @param definition 事务定义
     * @throws SQLException 如果执行 SQL 操作时出现异常
     */
    protected void prepareTransactionalConnection(Connection con, TransactionDefinition definition) throws SQLException {
        // 如果事务定义为只读事务
        if (definition.isReadOnly()) {
            try (Statement stmt = con.createStatement()) {
                // 执行设置事务为只读的 SQL 语句
                stmt.execute("set transaction read only");
            }
        }
    }

    /**
     * 数据源事务对象，继承自 JdbcTransactionObjectSupport
     */
    public static class DataSourceTransactionObject extends JdbcTransactionObjectSupport {
        // 是否为新的连接持有者
        private boolean newConnectionHolder;
        // 是否需要恢复自动提交模式
        private boolean mustRestoreAutoCommit;

        /**
         * 设置连接持有者
         *
         * @param connectionHolder    连接持有者
         * @param newConnectionHolder 是否为新的连接持有者
         */
        public void setConnectionHolder(ConnectionHolder connectionHolder, boolean newConnectionHolder) {
            super.setConnectionHolder(connectionHolder);
            this.newConnectionHolder = newConnectionHolder;
        }
    }
}
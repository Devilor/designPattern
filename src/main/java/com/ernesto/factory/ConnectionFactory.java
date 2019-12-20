package com.ernesto.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ernesto
 * @date 2019/12/20
 * <p>
 * 数据库连接工厂模式实现
 */
public class ConnectionFactory {

    private final static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
    // private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL =
        "jdbc:mysql://localhost:3306/spring-demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private final static String USER_NAME = "root";
    private final static String PASS_WORD = "123456";
    //数据库连接缓存对象
    private final ThreadLocal<Connection> connections = new ThreadLocal<Connection>();

    /**
     * 获取 数据库连接
     *
     * @return
     */
    public Connection getConnection() {
        // 先尝试从 ThreadLocal 中获取 Connection 实例
        Connection conn = connections.get();
        if (null == conn) {
            //如果没有则创建
            conn = this.createConnection();
            //并把创建好的 Connection 实例放入 ThreadLocal 中（缓存）
            connections.set(conn);
        }
        return conn;
    }

    /**
     * 创建 Connection 实例
     *
     * @return
     */
    public Connection createConnection() {
        Connection conn = null;
        try {
            // Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
            logger.info("Connection 实例创建成功！");
        } catch (Exception e) {
            logger.error("异常信息：{}", e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 设置 Connection 到 ThreadLocal 中
     *
     * @param conn
     */
    public void setConnection(Connection conn) {
        connections.set(conn);
    }

    /**
     * 关闭 Connection 资源，对于 ThreadLocal 来说就是移除当前的 Connection 实例
     *
     * @param conn
     */
    public void closeConnection(Connection conn) {
        connections.remove();
    }
}

package com.ernesto.utils;

import com.ernesto.property.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author Ernesto
 * @date 2019/12/22
 */
public class DBUtils {
    private final static Logger logger = LoggerFactory.getLogger(DBUtils.class);
    private final static String URL =
        "jdbc:mysql://localhost:3306/spring-demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private final static String USER_NAME = "root";
    private final static String PASS_WORD = "123456";

    private final ThreadLocal<Connection> connections = new ThreadLocal<Connection>();

    /**
     * 获取数据库连接对象 Connection
     *
     * @return
     */
    public Connection getConnection() {
        Connection conn = connections.get();
        if (null == conn) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
            } catch (SQLException e) {
                logger.error("数据库连接失败！");
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 关闭 Connection
     */
    public void closeConnection() {
        connections.remove();
    }

    /**
     * 设置 Connection
     *
     * @param connection
     */
    public void setConnection(Connection connection) {
        connections.set(connection);
    }

    /**
     * 根据 userName 获取 Order
     *
     * @param sql
     * @param param
     * @param clazz
     * @return
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Order selectByUserName(String sql, Object param, Class<Order> clazz)
        throws SQLException, IllegalAccessException, InstantiationException {
        Connection conn = this.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = null;
        pstm.setString(1, String.valueOf(param));
        rs = pstm.executeQuery();
        Order order = clazz.newInstance();
        while (rs.next()) {
            String orderId = rs.getString("order_id");
            String userId = rs.getString("user_id");
            String location = rs.getString("location");
            Double price = rs.getDouble("price");
            String telephone = rs.getString("telephone");
            order.setOrderId(orderId);
            order.setLocation(location);
            order.setPrice(price);
            order.setUserId(userId);
            order.setTelephone(telephone);
        }
        return order;
    }
}

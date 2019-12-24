package com.ernesto.decorator;

import java.util.Map;

/**
 * @author Ernesto
 * @date 2019/12/24
 */
public class Client {
    public static void main(String[] args) {
        //获取数据库连接
        DatabaseConnection conn = new OracleConnection();
        System.out.println(conn.getConnection());
        conn = new MysqlConnection();
        System.out.println(conn.getConnection());
        // 从数据库连接池中获取
        System.out.println("从数据库连接池中获取");
        DatabasePool pool = new DatabasePool(new MysqlConnection());
        Map<String, Object> connPool = pool.getConnPool();
        String connName = pool.getConnection();
        System.out.println("Connection:" + connName);
        for (Map.Entry<String, Object> conns : connPool.entrySet()) {
            String name = conns.getKey();
            System.out.println("---" + name);
        }
    }
}

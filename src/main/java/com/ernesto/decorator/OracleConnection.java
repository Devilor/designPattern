package com.ernesto.decorator;

/**
 * @author Ernesto
 * @date 2019/12/24
 */
public class OracleConnection implements DatabaseConnection {
    /**
     * 获取数据库连接的对象（规范定义）
     *
     * @return
     */
    public String getConnection() {
        return "Oracle Connection.";
    }
}

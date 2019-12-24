package com.ernesto.decorator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ernesto
 * @date 2019/12/24
 */
public class DatabasePool implements DatabaseConnection {
    private DatabaseConnection conn;
    private String keyName;
    /**
     * final 实例变量要么声明的时候直接初始化要么在构造函数中初始化（当做构造函数的参数或否都可以）
     */
    private final Map<String, Object> connPool;

    public DatabasePool(DatabaseConnection conn) {
        this.conn = conn;
        connPool = new HashMap<String, Object>();
        this.keyName = this.conn.getClass().getSimpleName();
        this.connPool.put(keyName, keyName);
    }

    /*public DatabasePool() {
        connPool = new HashMap<String, Object>();
    }*/

    /**
     * 获取数据库连接的对象（规范定义）
     *
     * @return
     */
    public String getConnection() {
        return (String)this.connPool.get(keyName);
    }

    public Map<String, Object> getConnPool() {
        return this.connPool;
    }
}

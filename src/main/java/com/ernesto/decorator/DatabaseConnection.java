package com.ernesto.decorator;

/**
 * @author Ernesto
 * @date 2019/12/24
 */
public interface DatabaseConnection {
    /**
     * 获取数据库连接的对象（规范定义）
     *
     * @return
     */
    String getConnection();
}

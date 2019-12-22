package com.ernesto.utils;

import com.ernesto.property.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * 从数据库中获取数据封装对象
 *
 * @author Ernesto
 * @date 2019/12/22
 */
public class LoadObjectByDB {
    private final static Logger logger = LoggerFactory.getLogger(LoadObjectByDB.class);

    private final static DBUtils utils = new DBUtils();

    public static Order loadOrder(String userName) {
        Order order = null;
        String querySql = "select * from t_order t where user_id = ? limit 1";
        try {
            order = utils.selectByUserName(querySql, userName, Order.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return order;
    }

}

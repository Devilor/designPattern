package com.ernesto.factory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * @author Ernesto
 * @date 2019/12/20
 */
public class ConnectionFactoryTest {
    private final static Logger logger = LoggerFactory.getLogger(ConnectionFactoryTest.class);

    @Test
    public void getConnectionTest() {
        ConnectionFactory factory = new ConnectionFactory();
        Connection conn = factory.getConnection();
        if (null == conn) {
            logger.info("Connection is Null!");
        } else {
            logger.info("Connection is OK!");
        }
    }
}

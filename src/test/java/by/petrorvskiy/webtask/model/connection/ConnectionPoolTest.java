package by.petrorvskiy.webtask.model.connection;

import by.petrorvskiy.webtask.exception.DaoException;
import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class ConnectionPoolTest {

    ConnectionPool connectionPool;
    Connection connection;

    @BeforeEach
    public void before() throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    @Name("getInstanceTest")
    @Test
    public void testGetInstance() {
        Assertions.assertNotNull(connection);
        connectionPool.releaseConnection(connection);
    }
}
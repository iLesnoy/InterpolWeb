package by.petrorvskiy.webtask.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * The {@link ConnectionPool} class has private BlockingQueue in which
 * ProxyConnections are stored.
 * The max amount of created connections is set by POOL_SIZE or
 * otherwise will be taken from DEFAULT_POOL_SIZE int value.
 * The connection can be taken from the BlockingQueue and
 * released to it.
 * Thread safe.
 *
 * @see ProxyConnection
 */
public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();
    private static final int ONCE = 1;
    private static final String POOL_PROPERTIES_ERROR = "initialization ConnectionPool Error";
    private static final String DRIVER_LOADING_ERROR = "JDBC driver loading Error";
    private static final int DEFAULT_POOL_SIZE = 8;
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;
    private static final String DATABASE_PASSWORD;
    private static final String DATABASE_USER;
    private static final String DATABASE_DRIVER;
    private static final CountDownLatch initialisingLatch = new CountDownLatch(ONCE);
    private static final AtomicBoolean isInstanceInitialized = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private final BlockingQueue<ProxyConnection> freeConnection;
    private final BlockingQueue<ProxyConnection> givenAwayConnections;

    static {
        try (InputStream inputStream = ConnectionPool.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            properties.load(inputStream);
            DATABASE_URL = properties.getProperty("db.url");
            DATABASE_PASSWORD = properties.getProperty("db.password");
            DATABASE_USER = properties.getProperty("db.user");
            DATABASE_DRIVER = properties.getProperty("db.driver");
            Class.forName(DATABASE_DRIVER);

        } catch (IOException e) {
            logger.fatal(POOL_PROPERTIES_ERROR, e);
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            logger.fatal(DRIVER_LOADING_ERROR, e);
            throw new RuntimeException();
        }
    }

    static Connection makeConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            logger.fatal(POOL_PROPERTIES_ERROR, e);
            throw new RuntimeException();
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            while (isInstanceInitialized.compareAndSet(false, true)) {
                instance = new ConnectionPool();
                initialisingLatch.countDown();
            }
            try {
                initialisingLatch.await();
            } catch (InterruptedException e) {
                logger.error( "Thread is interrupted while ConnectionPool is creating", e);
            }
        }
        return instance;
    }

    private ConnectionPool() {
        freeConnection = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            Connection connection = ConnectionPool.makeConnection();
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            freeConnection.add(proxyConnection);
        }
        if (freeConnection.isEmpty()) {
            logger.fatal("connections poll doesn't created");
            throw new RuntimeException("connection poll is empty ");
        }
        logger.info("Connection pool was created " + freeConnection.size());
    }


    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnection.take();
            givenAwayConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("getConnection method InterruptedException " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return connection;
    }


    public boolean releaseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            return false;
        }
        try {
            givenAwayConnections.remove(connection);
            freeConnection.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            logger.error("releaseConnection method InterruptedException " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return true;
    }


    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnection.take().reallyClose();
            } catch (InterruptedException exception) {
                logger.error("Error has occurred while destroying pool: " + exception.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("deregisterDrivers method SQLException" + e.getMessage());
            }
        });
    }

}
package dao.connection;

import dao.exception.connection.ConnectionPoolException;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final ConnectionPool INSTANCE = new ConnectionPool();
    DBResourceManager dbResourceManager = DBResourceManager.getInstance();

    private BlockingQueue<Connection> connectionQueue;

    private String driver = dbResourceManager.getValue(DBParameter.DB_DRIVER);
    private String url = dbResourceManager.getValue(DBParameter.DB_URL);
    private String user = dbResourceManager.getValue(DBParameter.DB_USER);
    private String password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
    private int poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public BlockingQueue<Connection> getConnectionQueue() {
        return connectionQueue;
    }

    public void initializeConnectionPool() throws ConnectionPoolException {

        try {
            Class.forName(driver);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(new PooledConnection(connection));
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Error during instantiation of the connection pool", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {

        }
        return connection;
    }

    public void closeConnectionQueue() throws ConnectionPoolException {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = connectionQueue.take();
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((PooledConnection) connection).reallyClose();
            } catch (SQLException | InterruptedException e) {
                throw new ConnectionPoolException("Exception occurs during the closing of the connection pool.", e);
            }
        }
    }
}

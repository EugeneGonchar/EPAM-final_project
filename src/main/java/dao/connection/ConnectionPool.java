package dao.connection;

import dao.exception.connection.ConnectionPoolException;

import java.sql.*;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private DBResourceManager dbResourceManager = DBResourceManager.getInstance();

    private static ConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    private int poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private ConnectionPool(){
        initConnectionPool();
    }

    public static ConnectionPool getInstance(){
        if (!instanceCreated.get()){
            lock.lock();
            try{
                if(!instanceCreated.get()){
                   instance = new ConnectionPool();
                   instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void initConnectionPool(){
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            buildingBlockingQueue(connectionQueue);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void buildingBlockingQueue(BlockingQueue<Connection> connectionQueue){
        for(int i = 0; i < poolSize; i++){
            try{
                Connection connection = ConnectionSetter.setConnection();
                connectionQueue.add(connection);
            } catch (ConnectionPoolException e){
                e.printStackTrace();
            }
        }
    }

    public BlockingQueue<Connection> getConnectionQueue() {
        return connectionQueue;
    }

    public void dispose(){
        clearConnectionQueue();
    }

    private void clearConnectionQueue(){
        try{
            closeConnectionsQueue(givenAwayConQueue);
            closeConnectionsQueue(connectionQueue);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection takeConnection(){
        Connection connection = null;
        try{
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(Statement statement, Connection connection){
        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return connection;
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = connectionQueue.take();
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((ProxyConnection) connection).reallyClose();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void deregisterAllDrivers() {
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

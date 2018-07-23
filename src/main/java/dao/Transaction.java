package dao;

import dao.connection.ConnectionPool;
import dao.connection.ProxyConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection = connectionPool.takeConnection();

    public void beginTransaction(AbstractDAO dao, AbstractDAO ... daos){
        try{
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dao.setConnection(connection);
        for(AbstractDAO oneDao : daos){
            oneDao.setConnection(connection);
        }
    }

    public void endTransaction(){
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void commit(){
        try{
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package dao;

import dao.connection.ConnectionPool;

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
        try{
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void commit(){
        try{
            connection.commit();
        } catch (SQLException e) {
            rollback();
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

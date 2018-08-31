package dao;

import dao.connection.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {

    private static final Logger logger = LogManager.getLogger(Transaction.class);

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection = connectionPool.takeConnection();

    public void beginTransaction(AbstractDAO dao, AbstractDAO ... daos){
        try{
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Beginning transaction failed!", e);
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
            logger.log(Level.ERROR, "Setting auto commit failed!", e);
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

    public void commit() throws SQLException{
            connection.commit();
    }

    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Rolling back failed!", e);
        }
    }
}

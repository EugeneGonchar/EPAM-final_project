package dao;

import dao.connection.ConnectionPool;
import dao.exception.connection.ConnectionPoolException;
import dao.impl.UserDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private static ConnectionPool connectionPool = null;

    private UserDAO userDAO;

    private DAOFactory(){
//        if (connectionPool == null){
//            connectionPool = ConnectionPool.getInstance();
//            try {
//                connectionPool.initializeConnectionPool();
//            } catch (ConnectionPoolException e1) {
//                e1.printStackTrace();
//            }
//        }
//        userDAO = new UserDAOImpl(connectionPool);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public static DAOFactory getInstance(){
        return instance;
    }
}

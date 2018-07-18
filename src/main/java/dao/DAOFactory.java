package dao;

import dao.connection.ConnectionPool;
import dao.impl.UserDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private UserDAO userDAO;

    private DAOFactory(){
        userDAO = new UserDAOImpl(connectionPool);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public static DAOFactory getInstance(){
        return instance;
    }
}

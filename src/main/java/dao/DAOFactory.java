package dao;

import dao.connection.ConnectionPool;
import dao.impl.UserDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private UserDAOImpl userDAOImpl;

    private DAOFactory(){
//        userDAO = new UserDAO(connectionPool);
    }

    public UserDAOImpl getUserDAO() {
        return userDAOImpl;
    }

    public static DAOFactory getInstance(){
        return instance;
    }
}

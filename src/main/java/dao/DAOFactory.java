package dao;

import dao.connection.ConnectionPool;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private UserDAO userDAO;

    private DAOFactory(){
//        userDAO = new UserDAO(connectionPool);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public static DAOFactory getInstance(){
        return instance;
    }
}

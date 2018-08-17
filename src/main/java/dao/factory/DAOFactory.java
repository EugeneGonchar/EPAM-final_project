package dao.factory;

import dao.*;
import dao.impl.*;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private static final AccidentDAO accidentDAO = new AccidentDAOImpl();
    private static final AddressDAO addressDAO = new AddressDAOImpl();
    private static final CarDAO carDAO = new CarDAOImpl();
    private static final OrderDAO orderDAO = new OrderDAOImpl();
    private static final OrderStatusDAO orderStatusDAO = new OrderStatusDAOImpl();
    private static final RoleDAO roleDAO = new RoleDAOImpl();
    private static final UserDAO userDAO = new UserDAOImpl();

    public static DAOFactory getInstance() {
        return instance;
    }

    public AccidentDAO getAccidentDAO() {
        return accidentDAO;
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public OrderStatusDAO getOrderStatusDAO() {
        return orderStatusDAO;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}

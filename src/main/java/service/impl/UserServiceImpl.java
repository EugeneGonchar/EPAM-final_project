package service.impl;

import dao.DAOFactory;
import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl {

//    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final UserDAOImpl userDAOImpl = new UserDAOImpl();

    public User logIn(String login, String password){

//        return daoFactory.getUserDAO().checkUser(login, password);
        return userDAOImpl.checkUser(login, password);
    }

    public boolean signUp(Map parameters){
        return userDAOImpl.isUserCreated(parameters);
    }
}

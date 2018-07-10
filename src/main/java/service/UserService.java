package service;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;

public class UserService {

//    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final UserDAO userDAO = new UserDAO();
    public User logIn(String login, String password){

//        return daoFactory.getUserDAO().checkUser(login, password);
        return userDAO.checkUser(login, password);
    }
}

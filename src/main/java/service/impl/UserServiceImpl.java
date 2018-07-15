package service.impl;

import dao.exception.EmailExistException;
import dao.exception.IncorrectLoginOrPasswordException;
import dao.exception.LoginExistException;
import dto.UserDTO;
import dao.impl.UserDAOImpl;
import entity.User;
import resource.MessageManager;
import service.UserService;
import service.exception.ExistEmptyFieldException;
import service.exception.PasswordShorter6SymbolsException;
import service.exception.PasswordsUnequalException;
import service.validation.Validator;

public class UserServiceImpl implements UserService {

//    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    public User logIn(UserDTO userDTO) throws ExistEmptyFieldException,
            IncorrectLoginOrPasswordException {
        if(Validator.isLoginOrPasswordFieldsEmpty(userDTO))
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));

//        return daoFactory.getUserDAO().checkUser(login, password);

        return userDAOImpl.checkUser(userDTO);
    }

    @Override
    public boolean signUp(UserDTO userDTO) throws ExistEmptyFieldException,
            PasswordShorter6SymbolsException,
            PasswordsUnequalException,
            EmailExistException,
            LoginExistException {

        if(Validator.isExistEmptyField(userDTO))
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));

        if(Validator.isPasswordShorter6Symbols(userDTO))
            throw new PasswordShorter6SymbolsException(MessageManager.getProperty("message.shortpassword"));

        if(!Validator.isPasswordsEqual(userDTO))
            throw new PasswordsUnequalException(MessageManager.getProperty("message.unequalpasswords"));

        return userDAOImpl.isUserCreated(userDTO);
    }

}

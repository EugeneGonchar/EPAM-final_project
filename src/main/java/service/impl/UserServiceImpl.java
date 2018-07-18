package service.impl;

import dao.DAOFactory;
import dao.UserDAO;
import dao.exception.user.EmailExistException;
import dao.exception.user.IncorrectLoginOrPasswordException;
import dao.exception.user.LoginExistException;
import dao.exception.user.WrongPasswordException;
import dto.UserDTO;
import entity.User;
import resource.MessageManager;
import service.UserService;
import service.exception.ExistEmptyFieldException;
import service.exception.PasswordShorter6SymbolsException;
import service.exception.PasswordsUnequalException;
import service.validation.Validator;

public class UserServiceImpl implements UserService {

    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public User logIn(UserDTO userDTO) throws ExistEmptyFieldException,
            IncorrectLoginOrPasswordException {
        if(Validator.isFieldsEmpty(userDTO.getLogin(), userDTO.getPassword())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }
        return userDAO.checkUser(userDTO);
    }

    @Override
    public void signUp(UserDTO userDTO) throws ExistEmptyFieldException,
            PasswordShorter6SymbolsException,
            PasswordsUnequalException,
            EmailExistException,
            LoginExistException {

        if(Validator.isFieldsEmpty(userDTO.getLogin(),
                userDTO.getPassword(),
                userDTO.getPassword2(),
                userDTO.getEmail(),
                userDTO.getPhone(),
                userDTO.getFirstName(),
                userDTO.getLastName())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        if(Validator.isPasswordShorter6Symbols(userDTO.getPassword())){
            throw new PasswordShorter6SymbolsException(MessageManager.getProperty("message.shortpassword"));
        }

        if(Validator.isPasswordsUnequal(userDTO.getPassword(), userDTO.getPassword2())){
            throw new PasswordsUnequalException(MessageManager.getProperty("message.unequalpasswords"));
        }/*переделать*/
        userDAO.isUserCreated(userDTO);
    }

    @Override
    public void changeNameSurname(UserDTO userDTO) throws ExistEmptyFieldException{
        if(Validator.isFieldsEmpty(userDTO.getFirstName(), userDTO.getLastName())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }
        userDAO.changeNameSurname(userDTO);
    }

    @Override
    public void changeLogin(UserDTO userDTO) throws ExistEmptyFieldException, LoginExistException{
        if(Validator.isFieldsEmpty(userDTO.getLogin())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }
        userDAO.changeLogin(userDTO);
    }

    @Override
    public void changePhone(UserDTO userDTO) throws ExistEmptyFieldException{
        if(Validator.isFieldsEmpty(userDTO.getPhone())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }
        userDAO.changePhone(userDTO);
    }

    @Override
    public void changeEmail(UserDTO userDTO) throws ExistEmptyFieldException, EmailExistException{
        if(Validator.isFieldsEmpty(userDTO.getEmail())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }
        userDAO.changeEmail(userDTO);
    }

    @Override
    public void changePassword(UserDTO userDTO) throws ExistEmptyFieldException,
            PasswordShorter6SymbolsException,
            PasswordsUnequalException,
            WrongPasswordException {
        if(Validator.isFieldsEmpty(userDTO.getPassword(), userDTO.getPassword2(), userDTO.getPassword3())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        if(Validator.isPasswordShorter6Symbols(userDTO.getPassword2())){
            throw new PasswordShorter6SymbolsException(MessageManager.getProperty("message.shortpassword"));
        }

        if(Validator.isPasswordsUnequal(userDTO.getPassword2(), userDTO.getPassword3())){
            throw new PasswordsUnequalException(MessageManager.getProperty("message.unequalpasswords"));
        }
        userDAO.changePassword(userDTO);
    }

}

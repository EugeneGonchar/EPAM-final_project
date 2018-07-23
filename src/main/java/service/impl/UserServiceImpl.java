package service.impl;

import dao.Transaction;
import service.exception.EmailExistException;
import service.exception.LoginExistException;
import service.exception.WrongPasswordException;
import dao.impl.UserDAOImpl;
import dto.UserDTO;
import entity.User;
import resource.MessageManager;
import service.UserService;
import service.exception.ExistEmptyFieldException;
import service.exception.PasswordShorter6SymbolsException;
import service.exception.PasswordsUnequalException;
import service.util.Hash;
import service.validation.Validator;

public class UserServiceImpl implements UserService {

    private final static int INVALID_ID = 0;

    @Override
    public User logIn(UserDTO userDTO) throws ExistEmptyFieldException{
        if(Validator.isFieldsEmpty(userDTO.getLogin(), userDTO.getPassword())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        User user = null;

        UserDAOImpl userDAO = new UserDAOImpl();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        user = userDAO.getUserByLogin(userDTO.getLogin());

        transaction.commit();
        transaction.endTransaction();

        if(user != null){
            String passwordHash = Hash.getCryptoSha256(userDTO.getPassword());
            String dbPasswordHash = user.getPassword();
            if(!Hash.isHashesEqual(passwordHash, dbPasswordHash)){
                user = null;
            }
        }

        return user;
    }

    public void signUp(UserDTO userDTO) throws ExistEmptyFieldException,
            PasswordShorter6SymbolsException,
            PasswordsUnequalException,
            EmailExistException,
            LoginExistException{
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
        }

        UserDAOImpl userDAO = new UserDAOImpl();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        if(userDAO.getUserIdByLogin(userDTO.getLogin()) != INVALID_ID){
            throw new LoginExistException();
        }
        if(userDAO.getUserIdByEmail(userDTO.getEmail()) != INVALID_ID){
            throw new EmailExistException();
        }

        userDTO.setPassword(Hash.getCryptoSha256(userDTO.getPassword()));
        userDAO.insertUser(userDTO);

        transaction.commit();
        transaction.endTransaction();
    }

    @Override
    public void changeNameSurname(UserDTO userDTO) throws ExistEmptyFieldException{
        if(Validator.isFieldsEmpty(userDTO.getFirstName(), userDTO.getLastName())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        UserDAOImpl userDAO = new UserDAOImpl();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        userDAO.updateNameSurname(userDTO);

        transaction.commit();
        transaction.endTransaction();
    }

    @Override
    public void changeLogin(UserDTO userDTO) throws ExistEmptyFieldException, LoginExistException{
        if(Validator.isFieldsEmpty(userDTO.getLogin())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        UserDAOImpl userDAO = new UserDAOImpl();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        if(userDAO.getUserIdByLogin(userDTO.getLogin()) != INVALID_ID){
            throw new LoginExistException();
        }
        userDAO.updateLogin(userDTO);

        transaction.commit();
        transaction.endTransaction();
    }

    @Override
    public void changePhone(UserDTO userDTO) throws ExistEmptyFieldException{
        if(Validator.isFieldsEmpty(userDTO.getPhone())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        UserDAOImpl userDAO = new UserDAOImpl();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        userDAO.updatePhone(userDTO);

        transaction.commit();
        transaction.endTransaction();
    }

    @Override
    public void changeEmail(UserDTO userDTO) throws ExistEmptyFieldException, EmailExistException{
        if(Validator.isFieldsEmpty(userDTO.getEmail())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        UserDAOImpl userDAO = new UserDAOImpl();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        if(userDAO.getUserIdByEmail(userDTO.getEmail()) != INVALID_ID){
            throw new EmailExistException();
        }
        userDAO.updateEmail(userDTO);

        transaction.commit();
        transaction.endTransaction();
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

        UserDAOImpl userDAO = new UserDAOImpl();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);
        String dbPassword = userDAO.getUserByLogin(userDTO.getLogin()).getPassword();
        String password = userDTO.getPassword();
        if(dbPassword != password){
            throw new WrongPasswordException();
        }
        userDTO.setPassword(Hash.getCryptoSha256(userDTO.getPassword()));
        userDAO.updatePassword(userDTO);

        transaction.commit();
        transaction.endTransaction();

    }

}

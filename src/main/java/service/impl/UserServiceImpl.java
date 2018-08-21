package service.impl;

import dao.Transaction;
import dao.UserDAO;
import dao.exception.dao.DAOException;
import dao.factory.DAOFactory;
import domain.dto.PageDTO;
import domain.dto.UserRoleDTO;
import service.exception.*;
import domain.dto.UserDTO;
import domain.entity.User;
import resource.MessageManager;
import service.UserService;
import service.util.Hash;
import service.validation.Validator;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final static int INVALID_ID = 0;

    @Override
    public User logIn(UserDTO userDTO) throws ServiceException,
            ExistEmptyFieldException,
            ExistFieldLonger50Symbols{
        if(Validator.isFieldsEmpty(userDTO.getLogin(), userDTO.getPassword())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }
        if(Validator.isFieldsLonger50Symbols(userDTO.getLogin(), userDTO.getPassword())){
            throw new ExistFieldLonger50Symbols(MessageManager.getProperty("message.longfield"));
        }

        User user = null;

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        try {
            user = userDAO.getUserByLogin(userDTO.getLogin());
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving user by login", e);
        } finally {
            transaction.endTransaction();
        }

        if(user != null){
            String passwordHash = Hash.getCryptoSha256(userDTO.getPassword());
            String dbPasswordHash = user.getPassword();
            if(!Hash.isHashesEqual(passwordHash, dbPasswordHash)){
                user = null;
            }
        }

        return user;
    }

    public void signUp(UserDTO userDTO) throws ServiceException, ExistEmptyFieldException,
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

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        try {
            if(userDAO.getUserIdByLogin(userDTO.getLogin()) != INVALID_ID){
                throw new LoginExistException();
            }
            if(userDAO.getUserIdByEmail(userDTO.getEmail()) != INVALID_ID){
                throw new EmailExistException();
            }

            userDTO.setPassword(Hash.getCryptoSha256(userDTO.getPassword()));
            userDAO.insertUser(userDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving user", e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void changeNameSurname(UserDTO userDTO) throws ServiceException, ExistEmptyFieldException{
        if(Validator.isFieldsEmpty(userDTO.getFirstName(), userDTO.getLastName())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);



        try {
            userDAO.updateNameSurname(userDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during updating user", e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void changeLogin(UserDTO userDTO) throws ServiceException, ExistEmptyFieldException, LoginExistException{
        if(Validator.isFieldsEmpty(userDTO.getLogin())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        try {
            if(userDAO.getUserIdByLogin(userDTO.getLogin()) != INVALID_ID){
                throw new LoginExistException();
            }
            userDAO.updateLogin(userDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during updating user's login", e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void changePhone(UserDTO userDTO) throws ServiceException, ExistEmptyFieldException{
        if(Validator.isFieldsEmpty(userDTO.getPhone())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        try {
            userDAO.updatePhone(userDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during updating user's phone", e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void changeEmail(UserDTO userDTO) throws ServiceException, ExistEmptyFieldException, EmailExistException{
        if(Validator.isFieldsEmpty(userDTO.getEmail())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        try {
            if(userDAO.getUserIdByEmail(userDTO.getEmail()) != INVALID_ID){
                throw new EmailExistException();
            }
            userDAO.updateEmail(userDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during updating user's email", e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void changePassword(UserDTO userDTO) throws ServiceException, ExistEmptyFieldException,
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

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        try {
            String dbHashedPassword = userDAO.getUserByLogin(userDTO.getLogin()).getPassword();
            String hashedPassword = Hash.getCryptoSha256(userDTO.getPassword());

            if(!Hash.isHashesEqual(dbHashedPassword, hashedPassword)){
                throw new WrongPasswordException(MessageManager.getProperty("message.passworderror"));
            }
            userDTO.setPassword(Hash.getCryptoSha256(userDTO.getPassword2()));
            userDAO.updatePassword(userDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during updating user's password", e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void checkDriverDetails(User user) throws ServiceException, ExistEmptyFieldException, EmailExistException{
        if(Validator.isFieldsEmpty(user.getEmail(),
                user.getPhone(),
                user.getFirstName(),
                user.getLastName())){
            throw new ExistEmptyFieldException(MessageManager.getProperty("message.emptyfield"));
        }

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        try {
            if(userDAO.getUserIdByEmail(user.getEmail()) != INVALID_ID){
                throw new EmailExistException();
            }
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving user's id by email", e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void updateUserImg(User user, String fileName) throws ServiceException{
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        try {
            userDAO.updateImageByUserId(user.getId(), fileName);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during updating user's image", e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<UserRoleDTO> getUserRoleList(PageDTO pageDTO) throws ServiceException{
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        List<UserRoleDTO> userRoleDTOList = null;
        Transaction transaction = new Transaction();

        transaction.beginTransaction(userDAO);

        try {
            pageDTO.setElementsCount(userDAO.getUsersWithRolesCount());
            pageDTO.calculatePagesCount();
            userRoleDTOList = userDAO.getUsersWithRoles(pageDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving list of user with their roles", e);
        } finally {
            transaction.endTransaction();
        }

        return userRoleDTOList;
    }
}

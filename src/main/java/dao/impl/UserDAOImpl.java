package dao.impl;

import dao.AbstractDAO;
import dao.exception.user.EmailExistException;
import dao.exception.user.IncorrectLoginOrPasswordException;
import dao.exception.user.LoginExistException;
import dao.exception.user.WrongPasswordException;
import dto.UserDTO;
import entity.User;
import service.util.Hash;
import resource.MessageManager;

import java.sql.*;
import java.util.List;

public class UserDAOImpl extends AbstractDAO{

    private static final String FIND_USER_BY_LOGIN = "SELECT `user_id`, `login`, `password`, `email`, `phone`, `first_name`, `last_name`, `role_id` FROM `user` WHERE `login`=?";
    private static final String FIND_PASSWORD_BY_LOGIN = "SELECT `password` FROM `user` WHERE `login`=?";
    private static final String FIND_USER_ID_BY_LOGIN = "SELECT `user_id` FROM `user` WHERE `login`=?";
    private static final String FIND_USER_ID_BY_EMAIL = "SELECT `user_id` FROM `user` WHERE `email`=?";
    /*private static final String SELECT_USERS_JOIN_ROLES =
            "SELECT `user_id`, `login`, `password`, `email`, `phone`, `first_name`, `last_name`, `role_id`" +
            "FROM `user` \n" +
            "JOIN `role`\n" +
            "ON `user`.`role_id` = `role`.`role_id`";*/

    private static final String INSERT_USER = "INSERT INTO `user`(`login`, `password`, `email`, `phone`, `first_name`, `last_name`) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_FIRST_NAME_LAST_NAME_BY_LOGIN = "UPDATE `user` SET `first_name`=?, `last_name`=? WHERE `login`=?";
    private static final String UPDATE_USER_LOGIN_BY_EMAIL = "UPDATE `user` SET `login`=? WHERE `email`=?";
    private static final String UPDATE_USER_PHONE_BY_LOGIN = "UPDATE `user` SET `phone`=? WHERE `login`=?";
    private static final String UPDATE_USER_EMAIL_BY_LOGIN = "UPDATE `user` SET `email`=? WHERE `login`=?";
    private static final String UPDATE_USER_PASSWORD_BY_LOGIN = "UPDATE `user` SET `password`=? WHERE `login`=?";

    private static final String TABLE_USER_FIELD_ID = "user_id";
    private static final String TABLE_USER_FIELD_LOGIN = "login";
    private static final String TABLE_USER_FIELD_EMAIL = "email";
    private static final String TABLE_USER_FIELD_PHONE = "phone";
    private static final String TABLE_USER_FIELD_FIRST_NAME = "first_name";
    private static final String TABLE_USER_FIELD_LAST_NAME = "last_name";
    private static final String TABLE_USER_FIELD_PASSWORD = "password";

    @Override
    public List<User> findAll(){
        /*List<User> userList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERS_JOIN_ROLES)){

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    public User getUserByLogin(String login){
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(TABLE_USER_FIELD_ID));
                user.setLogin(resultSet.getString(TABLE_USER_FIELD_LOGIN));
                user.setPassword(resultSet.getString(TABLE_USER_FIELD_PASSWORD));
                user.setEmail(resultSet.getString(TABLE_USER_FIELD_EMAIL));
                user.setPhone(resultSet.getString(TABLE_USER_FIELD_PHONE));
                user.setFirstName(resultSet.getString(TABLE_USER_FIELD_FIRST_NAME));
                user.setLastName(resultSet.getString(TABLE_USER_FIELD_LAST_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void isUserCreated(UserDTO userDTO) throws LoginExistException, EmailExistException{
        if(isValueExist(userDTO.getLogin(), FIND_USER_ID_BY_LOGIN)){
            throw new LoginExistException(MessageManager.getProperty("message.loginexist"));
        }

        if(isValueExist(userDTO.getEmail(), FIND_USER_ID_BY_EMAIL)){
            throw new EmailExistException(MessageManager.getProperty("message.emailexist"));
        }

        addUser(userDTO);
    }

    private boolean isValueExist(String field, String query){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, field);

            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                return false;
            }
        } catch (/*ConnectionPoolException | */SQLException e) {
            e.printStackTrace();
        } finally {
//            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return true;
    }

    public void changeNameSurname(UserDTO userDTO){
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(UPDATE_USER_FIRST_NAME_LAST_NAME_BY_LOGIN);

            preparedStatement.setString(1, userDTO.getFirstName());
            preparedStatement.setString(2, userDTO.getLastName());
            preparedStatement.setString(3, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (/*ConnectionPoolException | */SQLException e) {
            e.printStackTrace();
        } finally {
//            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    public void changeLogin(UserDTO userDTO) throws LoginExistException{
        PreparedStatement preparedStatement = null;

        try {
            if(isValueExist(userDTO.getLogin(), FIND_USER_ID_BY_LOGIN)){
                throw new LoginExistException(MessageManager.getProperty("message.loginexist"));
            }

            preparedStatement = connection.prepareStatement(UPDATE_USER_LOGIN_BY_EMAIL);

            preparedStatement.setString(1, userDTO.getLogin());
            preparedStatement.setString(2, userDTO.getEmail());

            preparedStatement.executeUpdate();
        } catch (/*ConnectionPoolException | */SQLException e) {
            e.printStackTrace();
        } finally {
//            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    public void changePhone(UserDTO userDTO){
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(UPDATE_USER_PHONE_BY_LOGIN);

            preparedStatement.setString(1, userDTO.getPhone());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (/*ConnectionPoolException | */SQLException e) {
            e.printStackTrace();
        } finally {
//            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    public void changeEmail(UserDTO userDTO) throws EmailExistException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            if(isValueExist(userDTO.getEmail(), FIND_USER_ID_BY_EMAIL)){
                throw new EmailExistException(MessageManager.getProperty("message.emailexist"));
            }

            preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL_BY_LOGIN);

            preparedStatement.setString(1, userDTO.getEmail());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (/*ConnectionPoolException | */SQLException e) {
            e.printStackTrace();
        } finally {
//            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }


    public void changePassword(UserDTO userDTO) throws WrongPasswordException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN);
            preparedStatement.setString(1, userDTO.getLogin());

            resultSet = preparedStatement.executeQuery();
            resultSet.next();/*чего блять????*/

            String passwordHash = Hash.getCryptoSha256(userDTO.getPassword());
            String dbPasswordHash = resultSet.getString(TABLE_USER_FIELD_PASSWORD);

            /*if(!Hash.hashEquality(passwordHash, dbPasswordHash)){
                throw new WrongPasswordException(MessageManager.getProperty("message.passworderror"));
            }*/

            String newPasswordHash = Hash.getCryptoSha256(userDTO.getPassword2());

            userDTO.setPassword(newPasswordHash);

            preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_LOGIN);

            preparedStatement.setString(1, newPasswordHash);
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (/*ConnectionPoolException | */SQLException e) {
            e.printStackTrace();
        } finally {
//            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }


    public void addUser(UserDTO userDTO){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, userDTO.getLogin());
            preparedStatement.setString(2, Hash.getCryptoSha256(userDTO.getPassword()));
            preparedStatement.setString(3, userDTO.getEmail());
            preparedStatement.setString(4, userDTO.getPhone());
            preparedStatement.setString(5, userDTO.getFirstName());
            preparedStatement.setString(6, userDTO.getLastName());

            preparedStatement.executeUpdate();
        } catch (/*ConnectionPoolException | */SQLException e) {
            e.printStackTrace();
        } finally {
//            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

}

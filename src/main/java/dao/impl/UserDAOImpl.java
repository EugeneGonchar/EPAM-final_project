package dao.impl;

import dao.connection.ConnectionPool;
import dao.connection.ConnectionPoolException;
import dao.exception.DAOException;
import dao.exception.EmailExistException;
import dao.exception.IncorrectLoginOrPasswordException;
import dao.exception.LoginExistException;
import dto.UserDTO;
import entity.User;
import dao.util.Hash;
import resource.MessageManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl {

//    private final ConnectionPool connectionPool;

    private final String FIND_USER_BY_LOGIN = "SELECT user_id, login, password, email, phone, first_name, last_name FROM `user` WHERE login=?";
    private final String FIND_USER_ID_BY_LOGIN = "SELECT user_id FROM `user` WHERE login=?";
    private final String FIND_USER_ID_BY_EMAIL = "SELECT user_id FROM `user` WHERE email=?";
    private final String INSERT_USER = "INSERT INTO `user`(login, password, email, phone, first_name, last_name) VALUES (?, ?, ?, ?, ?, ?)";

    private final String TABLE_USER_FIELD_ID = "user_id";
    private final String TABLE_USER_FIELD_LOGIN = "login";
    private final String TABLE_USER_FIELD_EMAIL = "email";
    private final String TABLE_USER_FIELD_PHONE = "phone";
    private final String TABLE_USER_FIELD_FIRST_NAME = "first_name";
    private final String TABLE_USER_FIELD_LAST_NAME = "last_name";
    private final String TABLE_USER_FIELD_PASSWORD = "password";

    public UserDAOImpl(){}

    /*public UserDAO(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }*/

    public User checkUser(UserDTO userDTO) throws IncorrectLoginOrPasswordException{

        User user = null;

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try{
//            connection = connectionPool.takeConnection();

            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, userDTO.getLogin());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next())
                throw new IncorrectLoginOrPasswordException(MessageManager.getProperty("message.loginpassworderror"));

            String passwordHash = Hash.getCryptoSha256(userDTO.getPassword());
            String dbPasswordHash = resultSet.getString(TABLE_USER_FIELD_PASSWORD);

            if(!Hash.hashEquality(passwordHash, dbPasswordHash))
                throw new IncorrectLoginOrPasswordException(MessageManager.getProperty("message.loginpassworderror"));

            user = new User();
            user.setId(resultSet.getInt(TABLE_USER_FIELD_ID));
            user.setLogin(resultSet.getString(TABLE_USER_FIELD_LOGIN));
            user.setPassword(resultSet.getString(TABLE_USER_FIELD_PASSWORD));
            user.setEmail(resultSet.getString(TABLE_USER_FIELD_EMAIL));
            user.setPhone(resultSet.getString(TABLE_USER_FIELD_PHONE));
            user.setFirstName(resultSet.getString(TABLE_USER_FIELD_FIRST_NAME));
            user.setLastName(resultSet.getString(TABLE_USER_FIELD_LAST_NAME));/*рефакторнуть и добавить константы*/

        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
            /*} finally {
             *//*if (connection != null && preparedStatement != null && resultSet != null){
                connectionPool.closeConnection(connection, preparedStatement, resultSet);
            }*//**/
        }
        return user;
    }

    public boolean isUserCreated(UserDTO userDTO) throws LoginExistException, EmailExistException{
        if(isFieldExist(userDTO.getLogin(), FIND_USER_ID_BY_LOGIN))
            throw new LoginExistException(MessageManager.getProperty("message.loginexist"));

        if(isFieldExist(userDTO.getEmail(), FIND_USER_ID_BY_EMAIL))
            throw new EmailExistException(MessageManager.getProperty("message.emailexist"));

        addUser(userDTO);
        return true;
    }

    public boolean isFieldExist(String field, String query){
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try{
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, field);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                return false;
            }
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }/*} finally {
         *//*if (connection != null && preparedStatement != null && resultSet != null){
                connectionPool.closeConnection(connection, preparedStatement, resultSet);
            }*//**/
        return true;
    }

    public void addUser(UserDTO userDTO){
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, userDTO.getLogin());
            preparedStatement.setString(2, Hash.getCryptoSha256(userDTO.getPassword()));
            preparedStatement.setString(3, userDTO.getEmail());
            preparedStatement.setString(4, userDTO.getPhone());
            preparedStatement.setString(5, userDTO.getFirstName());
            preparedStatement.setString(6, userDTO.getLastName());

            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }/*} finally {
         *//*if (connection != null && preparedStatement != null && resultSet != null){
                connectionPool.closeConnection(connection, preparedStatement, resultSet);
            }*//**/

    }

}

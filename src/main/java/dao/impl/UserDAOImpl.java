package dao.impl;

import dao.connection.ConnectionPool;
import dao.connection.ConnectionPoolException;
import entity.User;
import util.Hash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserDAOImpl {

//    private final ConnectionPool connectionPool;

    private final String FIND_USER_BY_LOGIN = "SELECT user_id, login, password, email, phone, first_name, last_name FROM `user` WHERE login=?";
    private final String FIND_USER_ID_BY_LOGIN = "SELECT user_id FROM `user` WHERE login=?";
    private final String INSERT_USER = "INSERT INTO `user`(login, password, email, phone, first_name, last_name) VALUES (?, ?, ?, ?, ?, ?)";


    public UserDAOImpl(){}

    /*public UserDAO(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }*/

    public User checkUser(String login, String password){

        User user = null;

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try{
//            connection = connectionPool.takeConnection();

            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next())
                return null;

            String passwordHash = Hash.getCryptoSha256(password);
            String dbPasswordHash = resultSet.getString("password");

            if(!Hash.hashEquality(passwordHash, dbPasswordHash))
                return null;

            user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));

        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
            /*} finally {
             *//*if (connection != null && preparedStatement != null && resultSet != null){
                connectionPool.closeConnection(connection, preparedStatement, resultSet);
            }*//**/
        }
        return user;
    }

    public void addUser(Map parameters){
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, (String) parameters.get("login"));
            preparedStatement.setString(2, Hash.getCryptoSha256((String) parameters.get("password")));
            preparedStatement.setString(3, (String) parameters.get("email"));
            preparedStatement.setString(4, (String) parameters.get("phone"));
            preparedStatement.setString(5, (String) parameters.get("first_name"));
            preparedStatement.setString(6, (String) parameters.get("last_name"));
            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserCreated(Map parameters){
        if (!isLoginExist((String) parameters.get("login"))){
            addUser(parameters);
            return true;
        } else return false;
    }

    public boolean isLoginExist(String login){
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try{
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ID_BY_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                return false;
            }
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}

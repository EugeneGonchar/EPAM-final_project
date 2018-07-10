package dao;

import dao.connection.ConnectionPool;
import dao.connection.ConnectionPoolException;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
//    private final ConnectionPool connectionPool;

    private final String FIND_USER_BY_LOGIN = "SELECT user_id, login, password, email, phone, first_name, last_name FROM `user` WHERE login=?";

    public UserDAO(){}

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
            if(!password.equals(resultSet.getString("password")))
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
}

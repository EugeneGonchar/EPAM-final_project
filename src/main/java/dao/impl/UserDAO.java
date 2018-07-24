package dao.impl;

import dao.AbstractDAO;
import dto.UserDTO;
import entity.User;

import java.sql.*;
import java.util.List;

public class UserDAO extends AbstractDAO{

    private static final String FIND_USER_BY_LOGIN = "SELECT `user_id`, `login`, `password`, `email`, `phone`, `first_name`, `last_name`, `role_id` FROM `user` WHERE `login`=?";
    private static final String FIND_USER_ID_BY_LOGIN = "SELECT `user_id` FROM `user` WHERE `login`=?";
    private static final String FIND_USER_ID_BY_EMAIL = "SELECT `user_id` FROM `user` WHERE `email`=?";

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

    public int getUserIdByLogin(String login){
        return getUserIdByValue(login, FIND_USER_ID_BY_LOGIN);
    }

    public int getUserIdByEmail(String email){
        return getUserIdByValue(email, FIND_USER_ID_BY_EMAIL);
    }

    private int getUserIdByValue(String value, String query){
        int id = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                id = resultSet.getInt(TABLE_USER_FIELD_ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void insertUser(UserDTO userDTO){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)){
            preparedStatement.setString(1, userDTO.getLogin());
            preparedStatement.setString(2, userDTO.getPassword());
            preparedStatement.setString(3, userDTO.getEmail());
            preparedStatement.setString(4, userDTO.getPhone());
            preparedStatement.setString(5, userDTO.getFirstName());
            preparedStatement.setString(6, userDTO.getLastName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNameSurname(UserDTO userDTO){
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_FIRST_NAME_LAST_NAME_BY_LOGIN)){
            preparedStatement.setString(1, userDTO.getFirstName());
            preparedStatement.setString(2, userDTO.getLastName());
            preparedStatement.setString(3, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLogin(UserDTO userDTO){
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_LOGIN_BY_EMAIL)){
            preparedStatement.setString(1, userDTO.getLogin());
            preparedStatement.setString(2, userDTO.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePhone(UserDTO userDTO){
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PHONE_BY_LOGIN)){
            preparedStatement.setString(1, userDTO.getPhone());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmail(UserDTO userDTO){
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL_BY_LOGIN)){
            preparedStatement.setString(1, userDTO.getEmail());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(UserDTO userDTO) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_LOGIN)) {
            preparedStatement.setString(1, userDTO.getPassword());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

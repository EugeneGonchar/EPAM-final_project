package dao.impl;

import static dao.util.DBFieldName.*;

import dao.UserDAO;
import dao.util.DomainCreator;
import dao.util.QueryBuilder;
import domain.dto.PageDTO;
import domain.dto.UserDTO;
import domain.dto.UserRoleDTO;
import domain.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAOImpl extends UserDAO {

    private static final String INSERT_USER = "INSERT INTO `user`(`login`, `password`, `email`, `phone`, `first_name`, `last_name`) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_USERS_COUNT = "SELECT COUNT(*) AS `count` FROM `user`";
    private static final String SELECT_USER_BY_LOGIN = "SELECT `user_id`, `login`, `password`, `email`, `phone`, `first_name`, `last_name`, `role_id`, `profile_image` FROM `user` WHERE `login`=?";
    private static final String SELECT_USER_ID_BY_LOGIN = "SELECT `user_id` FROM `user` WHERE `login`=?";
    private static final String SELECT_USER_ID_BY_EMAIL = "SELECT `user_id` FROM `user` WHERE `email`=?";
    private static final String SELECT_USERS_WITH_ROLES = "SELECT `user`.`user_id`, `user`.`first_name`, `user`.`last_name`, `user`.`phone`, `user`.`login`, `user`.`email`, `user`.`role_id`, `role`.`name` AS `role`\n" +
            "FROM `user`\n" +
            "JOIN `role`\n" +
            "ON `role`.`role_id` = `user`.`role_id`\n" +
            "ORDER BY `user`.`first_name`, `user`.`last_name`";

    private static final String UPDATE_USER_FIRST_NAME_LAST_NAME_BY_LOGIN = "UPDATE `user` SET `first_name`=?, `last_name`=? WHERE `login`=?";
    private static final String UPDATE_USER_LOGIN_BY_EMAIL = "UPDATE `user` SET `login`=? WHERE `email`=?";
    private static final String UPDATE_USER_PHONE_BY_LOGIN = "UPDATE `user` SET `phone`=? WHERE `login`=?";
    private static final String UPDATE_USER_EMAIL_BY_LOGIN = "UPDATE `user` SET `email`=? WHERE `login`=?";
    private static final String UPDATE_USER_PASSWORD_BY_LOGIN = "UPDATE `user` SET `password`=? WHERE `login`=?";
    private static final String UPDATE_USER_IMAGE_BY_USER_ID = "UPDATE `user` SET `profile_image`=? WHERE `user_id` = ?";

    @Override
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

    @Override
    public void insertUser(User user){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getFirstName());
            preparedStatement.setString(6, user.getLastName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll(){
        /*List<User> userList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_JOIN_ROLES)){

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public User getUserByLogin(String login){
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                user = DomainCreator.createFullUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public int getUserIdByLogin(String login){
        return getUserIdByValue(login, SELECT_USER_ID_BY_LOGIN);
    }

    @Override
    public int getUserIdByEmail(String email){
        return getUserIdByValue(email, SELECT_USER_ID_BY_EMAIL);
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

    @Override
    public int getUsersWithRolesCount(){
        return getElementsCount(SELECT_USERS_COUNT);
    }

    @Override
    public List<UserRoleDTO> getUsersWithRoles(PageDTO pageDTO){
        List<UserRoleDTO> userRoleDTOList = new LinkedList<>();
        UserRoleDTO userRoleDTO = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.setQueryLimit(SELECT_USERS_WITH_ROLES, pageDTO))){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                userRoleDTO = DomainCreator.createUserRoleDTO(resultSet);
                userRoleDTOList.add(userRoleDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoleDTOList;
    }

    @Override
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

    @Override
    public void updateLogin(UserDTO userDTO){
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_LOGIN_BY_EMAIL)){
            preparedStatement.setString(1, userDTO.getLogin());
            preparedStatement.setString(2, userDTO.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePhone(UserDTO userDTO){
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PHONE_BY_LOGIN)){
            preparedStatement.setString(1, userDTO.getPhone());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmail(UserDTO userDTO){
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL_BY_LOGIN)){
            preparedStatement.setString(1, userDTO.getEmail());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(UserDTO userDTO) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_LOGIN)) {
            preparedStatement.setString(1, userDTO.getPassword());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateImageByUserId(int id, String fileName){
        updateImageNameById(UPDATE_USER_IMAGE_BY_USER_ID, fileName, id);
    }
}

package dao.impl;

import static dao.util.DBFieldName.*;

import dao.UserDAO;
import dao.exception.dao.DAOException;
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

    private static final String SELECT_USERS_COUNT = "SELECT COUNT(*) AS `count` FROM `dao`";
    private static final String SELECT_USER_BY_LOGIN = "SELECT `user_id`, `login`, `password`, `email`, `phone`, `first_name`, `last_name`, `role_id`, `profile_image` FROM `user` WHERE `login`=?";
    private static final String SELECT_USER_ID_BY_LOGIN = "SELECT `user_id` FROM `dao` WHERE `login`=?";
    private static final String SELECT_USER_ID_BY_EMAIL = "SELECT `user_id` FROM `dao` WHERE `email`=?";
    private static final String SELECT_USERS_WITH_ROLES = "SELECT `dao`.`user_id`, `dao`.`first_name`, `dao`.`last_name`, `dao`.`phone`, `dao`.`login`, `dao`.`email`, `dao`.`role_id`, `role`.`name` AS `role`\n" +
            "FROM `dao`\n" +
            "JOIN `role`\n" +
            "ON `role`.`role_id` = `dao`.`role_id`\n" +
            "ORDER BY `dao`.`first_name`, `dao`.`last_name`";

    private static final String UPDATE_USER_FIRST_NAME_LAST_NAME_BY_LOGIN = "UPDATE `user` SET `first_name`=?, `last_name`=? WHERE `login`=?";
    private static final String UPDATE_USER_LOGIN_BY_EMAIL = "UPDATE `user` SET `login`=? WHERE `email`=?";
    private static final String UPDATE_USER_PHONE_BY_LOGIN = "UPDATE `user` SET `phone`=? WHERE `login`=?";
    private static final String UPDATE_USER_EMAIL_BY_LOGIN = "UPDATE `user` SET `email`=? WHERE `login`=?";
    private static final String UPDATE_USER_PASSWORD_BY_LOGIN = "UPDATE `user` SET `password`=? WHERE `login`=?";
    private static final String UPDATE_USER_IMAGE_BY_USER_ID = "UPDATE `dao` SET `profile_image`=? WHERE `user_id` = ?";

    @Override
    public void insertUser(UserDTO userDTO) throws DAOException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)){
            preparedStatement.setString(1, userDTO.getLogin());
            preparedStatement.setString(2, userDTO.getPassword());
            preparedStatement.setString(3, userDTO.getEmail());
            preparedStatement.setString(4, userDTO.getPhone());
            preparedStatement.setString(5, userDTO.getFirstName());
            preparedStatement.setString(6, userDTO.getLastName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception throws during inserting user", e);
        }
    }

    @Override
    public void insertUser(User user) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getFirstName());
            preparedStatement.setString(6, user.getLastName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception throws during inserting user", e);
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
    public User getUserByLogin(String login) throws DAOException{
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                user = DomainCreator.createFullUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving user by login", e);
        }

        return user;
    }

    @Override
    public int getUserIdByLogin(String login) throws DAOException{
        return getUserIdByValue(login, SELECT_USER_ID_BY_LOGIN);
    }

    @Override
    public int getUserIdByEmail(String email) throws DAOException{
        return getUserIdByValue(email, SELECT_USER_ID_BY_EMAIL);
    }

    private int getUserIdByValue(String value, String query) throws DAOException{
        int id = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                id = resultSet.getInt(TABLE_USER_FIELD_ID);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving user id by parameter", e);
        }
        return id;
    }

    @Override
    public int getUsersWithRolesCount() throws DAOException{
        return getElementsCount(SELECT_USERS_COUNT);
    }

    @Override
    public List<UserRoleDTO> getUsersWithRoles(PageDTO pageDTO) throws DAOException{
        List<UserRoleDTO> userRoleDTOList = new LinkedList<>();
        UserRoleDTO userRoleDTO = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.setQueryLimit(SELECT_USERS_WITH_ROLES, pageDTO))){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                userRoleDTO = DomainCreator.createUserRoleDTO(resultSet);
                userRoleDTOList.add(userRoleDTO);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving list of users with roles", e);
        }
        return userRoleDTOList;
    }

    @Override
    public void updateNameSurname(UserDTO userDTO) throws DAOException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_FIRST_NAME_LAST_NAME_BY_LOGIN)){
            preparedStatement.setString(1, userDTO.getFirstName());
            preparedStatement.setString(2, userDTO.getLastName());
            preparedStatement.setString(3, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception throws during updating user's name and surname", e);
        }
    }

    @Override
    public void updateLogin(UserDTO userDTO) throws DAOException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_LOGIN_BY_EMAIL)){
            preparedStatement.setString(1, userDTO.getLogin());
            preparedStatement.setString(2, userDTO.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception throws during updating user's login", e);
        }
    }

    @Override
    public void updatePhone(UserDTO userDTO) throws DAOException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PHONE_BY_LOGIN)){
            preparedStatement.setString(1, userDTO.getPhone());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception throws during updating user's phone", e);
        }
    }

    @Override
    public void updateEmail(UserDTO userDTO) throws DAOException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL_BY_LOGIN)){
            preparedStatement.setString(1, userDTO.getEmail());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception throws during updating user's email", e);
        }
    }

    @Override
    public void updatePassword(UserDTO userDTO) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_LOGIN)) {
            preparedStatement.setString(1, userDTO.getPassword());
            preparedStatement.setString(2, userDTO.getLogin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception throws during updating user's password", e);
        }
    }

    @Override
    public void updateImageByUserId(int id, String fileName) throws DAOException{
        updateImageNameById(UPDATE_USER_IMAGE_BY_USER_ID, fileName, id);
    }
}

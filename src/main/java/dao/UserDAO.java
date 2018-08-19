package dao;

import dao.exception.dao.DAOException;
import domain.dto.PageDTO;
import domain.dto.UserDTO;
import domain.dto.UserRoleDTO;
import domain.entity.User;

import java.util.List;

public abstract class UserDAO extends AbstractDAO {
    public abstract void insertUser(UserDTO userDTO) throws DAOException;

    public abstract void insertUser(User user) throws DAOException;

    public abstract User getUserByLogin(String login) throws DAOException;

    public abstract int getUserIdByLogin(String login) throws DAOException;

    public abstract int getUserIdByEmail(String email) throws DAOException;

    public abstract int getUsersWithRolesCount() throws DAOException;

    public abstract List<UserRoleDTO> getUsersWithRoles(PageDTO pageDTO) throws DAOException;

    public abstract void updateNameSurname(UserDTO userDTO) throws DAOException;

    public abstract void updateLogin(UserDTO userDTO) throws DAOException;

    public abstract void updatePhone(UserDTO userDTO) throws DAOException;

    public abstract void updateEmail(UserDTO userDTO) throws DAOException;

    public abstract void updatePassword(UserDTO userDTO) throws DAOException;

    public abstract void updateImageByUserId(int id, String fileName) throws DAOException;
}

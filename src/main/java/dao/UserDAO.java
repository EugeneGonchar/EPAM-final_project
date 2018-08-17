package dao;

import pojo.dto.PageDTO;
import pojo.dto.UserDTO;
import pojo.dto.UserRoleDTO;
import pojo.entity.User;

import java.util.List;

public abstract class UserDAO extends AbstractDAO {
    public abstract void insertUser(UserDTO userDTO);

    public abstract void insertUser(User user);

    public abstract User getUserByLogin(String login);

    public abstract int getUserIdByLogin(String login);

    public abstract int getUserIdByEmail(String email);

    public abstract int getUsersWithRolesCount();

    public abstract List<UserRoleDTO> getUsersWithRoles(PageDTO pageDTO);

    public abstract void updateNameSurname(UserDTO userDTO);

    public abstract void updateLogin(UserDTO userDTO);

    public abstract void updatePhone(UserDTO userDTO);

    public abstract void updateEmail(UserDTO userDTO);

    public abstract void updatePassword(UserDTO userDTO);

    public abstract void updateImageByUserId(int id, String fileName);
}

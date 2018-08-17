package dao.impl;

import static dao.util.DBFieldName.*;

import dao.AbstractDAO;
import dao.RoleDAO;
import pojo.entity.Role;
import pojo.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDAOImpl extends RoleDAO {

    private static final String SELECT_ROLES_COUNT = "SELECT COUNT(*) AS `count` FROM `role`";
    private static final String SELECT_ROLE_BY_ID = "SELECT `role_id`, `name` FROM `role` WHERE `role_id` = ?";

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Role getRoleByUser(User user){
        Role role = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID)){
            preparedStatement.setInt(1, user.getRoleId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                role = new Role();
                role.setId(resultSet.getInt(TABLE_ROLE_FIELD_ROLE_ID));
                role.setName(resultSet.getString(TABLE_ROLE_FIELD_NAME));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
}

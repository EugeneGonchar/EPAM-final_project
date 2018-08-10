package dao.impl;

import static dao.util.DBFieldName.*;

import dao.AbstractDAO;
import pojo.entity.Role;
import pojo.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDAO extends AbstractDAO {

    private static final String GET_ROLES_COUNT = "SELECT COUNT(*) AS `count` FROM `role`";

    private static final String FIND_ROLE_BY_USER = "SELECT `role_id`, `name` FROM `role` WHERE `role_id` = ?";

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public int getCount(){
        return getElementsCount(GET_ROLES_COUNT);
    }

    public Role getRoleByUser(User user){
        Role role = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROLE_BY_USER)){
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

package dao.impl;

import static dao.util.DBFieldName.*;

import dao.RoleDAO;
import dao.util.DomainCreator;
import domain.entity.Role;

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
    public Role getRoleById(int id){
        Role role = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                role = DomainCreator.createRole(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
}

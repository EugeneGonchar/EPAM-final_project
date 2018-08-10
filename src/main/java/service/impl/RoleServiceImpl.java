package service.impl;

import dao.Transaction;
import dao.impl.RoleDAO;
import pojo.entity.Role;
import pojo.entity.User;
import service.RoleService;

import java.sql.SQLException;

public class RoleServiceImpl implements RoleService {

    @Override
    public Role getRoleOfUser(User user){
        Role role = null;

        Transaction transaction = new Transaction();
        RoleDAO roleDAO = new RoleDAO();

        transaction.beginTransaction(roleDAO);

        role = roleDAO.getRoleByUser(user);

        try{
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
        }
        transaction.endTransaction();

        return role;
    }
}

package service.impl;

import dao.RoleDAO;
import dao.Transaction;
import dao.factory.DAOFactory;
import domain.entity.Role;
import domain.entity.User;
import service.RoleService;

import java.sql.SQLException;

public class RoleServiceImpl implements RoleService {

    @Override
    public Role getRoleOfUser(User user){
        Role role = null;

        Transaction transaction = new Transaction();
        RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();

        transaction.beginTransaction(roleDAO);

        role = roleDAO.getRoleById(user.getRoleId());

        try{
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
        }
        transaction.endTransaction();

        return role;
    }
}

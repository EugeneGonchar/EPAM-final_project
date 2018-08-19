package service.impl;

import dao.RoleDAO;
import dao.Transaction;
import dao.exception.dao.DAOException;
import dao.factory.DAOFactory;
import domain.entity.Role;
import domain.entity.User;
import service.RoleService;
import service.exception.ServiceException;

import java.sql.SQLException;

public class RoleServiceImpl implements RoleService {

    @Override
    public Role getRoleOfUser(User user) throws ServiceException {
        Role role = null;

        Transaction transaction = new Transaction();
        RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();

        transaction.beginTransaction(roleDAO);

        try {
            role = roleDAO.getRoleById(user.getRoleId());
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving user's role", e);
        } finally {
            transaction.endTransaction();
        }

        return role;
    }
}

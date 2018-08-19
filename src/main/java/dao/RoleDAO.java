package dao;

import dao.exception.dao.DAOException;
import domain.entity.Role;

public abstract class RoleDAO extends AbstractDAO {
    public abstract Role getRoleById(int id) throws DAOException;
}

package dao;

import domain.entity.Role;

public abstract class RoleDAO extends AbstractDAO {
    public abstract Role getRoleById(int id);
}

package dao;

import pojo.entity.Role;
import pojo.entity.User;

public abstract class RoleDAO extends AbstractDAO {
    public abstract Role getRoleByUser(User user);
}

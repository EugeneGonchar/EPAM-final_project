package service;

import domain.entity.Role;
import domain.entity.User;

public interface RoleService {

    Role getRoleOfUser(User user);

}

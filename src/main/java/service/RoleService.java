package service;

import pojo.entity.Role;
import pojo.entity.User;

public interface RoleService {

    Role getRoleOfUser(User user);

}

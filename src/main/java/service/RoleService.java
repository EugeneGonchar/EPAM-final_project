package service;

import domain.entity.Role;
import domain.entity.User;
import service.exception.ServiceException;

public interface RoleService {

    Role getRoleOfUser(User user) throws ServiceException;

}

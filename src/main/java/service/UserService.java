package service;

import domain.dto.PageDTO;
import domain.dto.UserRoleDTO;
import service.exception.*;
import domain.dto.UserDTO;
import domain.entity.User;

import java.util.List;

public interface UserService {

    User logIn(UserDTO userDTO) throws ServiceException, ExistFieldLonger50Symbols;

    void signUp(UserDTO userDTO) throws
            EmailExistException,
            LoginExistException, ServiceException, PassportDataInvalidException;

    void changeNameSurname(UserDTO userDTO) throws ServiceException;

    void changeLogin(UserDTO userDTO) throws
            LoginExistException, ServiceException;

    void changePhone(UserDTO userDTO) throws ServiceException;

    void changeEmail(UserDTO userDTO) throws EmailExistException, ServiceException;

    void changePassword(UserDTO userDTO) throws
            WrongPasswordException, ServiceException;

    void checkDriverDetails(User user) throws EmailExistException, ServiceException;

    List<UserRoleDTO> getUserRoleList(PageDTO pageDTO) throws ServiceException;

    void updateUserImg(User user, String fileName) throws ServiceException;

}

package service;

import pojo.dto.PageDTO;
import pojo.dto.UserRoleDTO;
import service.exception.EmailExistException;
import service.exception.LoginExistException;
import service.exception.WrongPasswordException;
import pojo.dto.UserDTO;
import pojo.entity.User;
import service.exception.ExistEmptyFieldException;
import service.exception.PasswordShorter6SymbolsException;
import service.exception.PasswordsUnequalException;

import java.util.List;

public interface UserService {

    User logIn(UserDTO userDTO) throws ExistEmptyFieldException;

    void signUp(UserDTO userDTO) throws ExistEmptyFieldException,
            PasswordShorter6SymbolsException,
            PasswordsUnequalException,
            EmailExistException,
            LoginExistException;

    void changeNameSurname(UserDTO userDTO) throws ExistEmptyFieldException;

    void changeLogin(UserDTO userDTO) throws ExistEmptyFieldException,
            LoginExistException;

    void changePhone(UserDTO userDTO) throws ExistEmptyFieldException;

    void changeEmail(UserDTO userDTO) throws ExistEmptyFieldException, EmailExistException;

    void changePassword(UserDTO userDTO) throws ExistEmptyFieldException,
            PasswordShorter6SymbolsException,
            PasswordsUnequalException,
            WrongPasswordException;

    void checkDriverDetails(User user) throws ExistEmptyFieldException, EmailExistException;

    List<UserRoleDTO> getUserRoleList(PageDTO pageDTO);

}

package service;

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

    public User logIn(UserDTO userDTO) throws ExistEmptyFieldException;

    public void signUp(UserDTO userDTO) throws ExistEmptyFieldException,
            PasswordShorter6SymbolsException,
            PasswordsUnequalException,
            EmailExistException,
            LoginExistException;

    public void changeNameSurname(UserDTO userDTO) throws ExistEmptyFieldException;

    public void changeLogin(UserDTO userDTO) throws ExistEmptyFieldException,
            LoginExistException;

    public void changePhone(UserDTO userDTO) throws ExistEmptyFieldException;

    public void changeEmail(UserDTO userDTO) throws ExistEmptyFieldException, EmailExistException;

    public void changePassword(UserDTO userDTO) throws ExistEmptyFieldException,
            PasswordShorter6SymbolsException,
            PasswordsUnequalException,
            WrongPasswordException;

    public void checkDriverDetails(User user) throws ExistEmptyFieldException, EmailExistException;

    public List<UserRoleDTO> getUserRoleList();

}

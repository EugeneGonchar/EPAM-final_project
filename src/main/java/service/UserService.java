package service;

import service.exception.EmailExistException;
import service.exception.LoginExistException;
import service.exception.WrongPasswordException;
import dto.UserDTO;
import entity.User;
import service.exception.ExistEmptyFieldException;
import service.exception.PasswordShorter6SymbolsException;
import service.exception.PasswordsUnequalException;

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

}

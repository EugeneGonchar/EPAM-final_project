package service;

import dao.exception.EmailExistException;
import dao.exception.IncorrectLoginOrPasswordException;
import dao.exception.LoginExistException;
import dto.UserDTO;
import entity.User;
import service.exception.ExistEmptyFieldException;
import service.exception.PasswordShorter6SymbolsException;
import service.exception.PasswordsUnequalException;

public interface UserService {

    public User logIn(UserDTO userDTO) throws ExistEmptyFieldException, IncorrectLoginOrPasswordException;

    public boolean signUp(UserDTO userDTO) throws ExistEmptyFieldException,
            PasswordShorter6SymbolsException,
            PasswordsUnequalException,
            EmailExistException,
            LoginExistException;

}

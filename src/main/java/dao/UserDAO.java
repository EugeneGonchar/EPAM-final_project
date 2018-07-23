package dao;

import service.exception.EmailExistException;
import service.exception.IncorrectLoginOrPasswordException;
import service.exception.LoginExistException;
import service.exception.WrongPasswordException;
import dto.UserDTO;
import entity.User;

public interface UserDAO {

    public User checkUser(UserDTO userDTO) throws IncorrectLoginOrPasswordException;

    public void isUserCreated(UserDTO userDTO) throws LoginExistException, EmailExistException;

    public void changeNameSurname(UserDTO userDTO);

    public void changeLogin(UserDTO userDTO) throws LoginExistException;

    public void changePhone(UserDTO userDTO);

    public void changeEmail(UserDTO userDTO) throws EmailExistException;

    public void changePassword(UserDTO userDTO) throws WrongPasswordException;

    public void addUser(UserDTO userDTO);

}

package dao;

import entity.User;

public interface UserDAO {

    public User checkUser(String login, String password);

}

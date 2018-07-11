package service;

import entity.User;

public interface UserService {
    public User logIn(String login, String password);
}

package by.voluevich.controllers;

import by.voluevich.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserController {

    void addUser(User user) throws SQLException;

    void deleteUserById(int id) throws SQLException;

    void deleteUserByLogin(String login) throws SQLException;

    List<User> findUsersByAddress(int addressId);

    List<User> findUsersByTelephone(int telephone);

    List<User> getUsers();

    User findUserByLogin(String login);

    User findUserById(int id);

    List<String> findAllByName();

    void addAddressForUser (int userId, int addressId);

    void addTelephoneForUser (int userId, int telephoneId);

    boolean isExistUserById(int id) throws SQLException;

    boolean isExistUserByLogin(String login) throws SQLException;
}

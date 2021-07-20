package by.voluevich.controllers;

import by.voluevich.entity.User;
import by.voluevich.service.utils.Dependencies;

import java.sql.SQLException;
import java.util.List;

public class UserControllerImpl implements UserController {

    @Override
    public void addUser(User user) throws SQLException {
        Dependencies.getUsersDao().addUser(user);
    }

    @Override
    public void deleteUserById(int id) throws SQLException {
        Dependencies.getUsersDao().deleteUserById(id);
    }

    @Override
    public void deleteUserByLogin(String login) throws SQLException {
        Dependencies.getUsersDao().deleteUserByLogin(login);
    }

    @Override
    public List<User> findUsersByAddress(int addressId) {
        return Dependencies.getUsersDao().findUsersByAddress(addressId);
    }

    @Override
    public List<User> findUsersByTelephone(int telephone) {
        return Dependencies.getUsersDao().findUsersByTelephone(telephone);
    }

    @Override
    public List<User> getUsers() {
        return Dependencies.getUsersDao().getUsers();
    }

    @Override
    public User findUserByLogin(String login) {
        return Dependencies.getUsersDao().findUserByLogin(login);
    }

    @Override
    public User findUserById(int id) {
        return Dependencies.getUsersDao().findUserById(id);
    }

    @Override
    public List<String> findAllByName() {
        return Dependencies.getUsersDao().findAllByName();
    }

    @Override
    public void addAddressForUser(int userId, int addressId) {
        Dependencies.getUsersDao().addAddressForUser(userId, addressId);
    }

    @Override
    public void addTelephoneForUser(int userId, int telephoneId) {
    Dependencies.getUsersDao().addTelephoneForUser(userId, telephoneId);
    }

    @Override
    public boolean isExistUserById(int id) throws SQLException {
        return Dependencies.getUsersDao().isExistUserById(id);
    }

    @Override
    public boolean isExistUserByLogin(String login) throws SQLException {
        return Dependencies.getUsersDao().isExistUserByLogin(login);
    }
}

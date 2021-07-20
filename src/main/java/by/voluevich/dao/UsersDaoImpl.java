package by.voluevich.dao;

import by.voluevich.entity.Address;
import by.voluevich.entity.Telephone;
import by.voluevich.entity.User;
import by.voluevich.service.exception.IdNotFoundException;
import by.voluevich.service.exception.LoginNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

    @Override
    public void addUser(User user) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try {
            connection.setAutoCommit(false);
            if (!isExistUserByLogin(user.getLogin())) {
                String query = "INSERT INTO scool.users (login, password) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.execute();

                String query2 = "SELECT * FROM scool.users WHERE users.login = ?";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setString(1, user.getLogin());
                ResultSet resultSet = preparedStatement2.executeQuery();
                resultSet.next();
                int idNewUser = resultSet.getInt("user_id");

                String query3 = "INSERT INTO scool.users_address VALUES (?, ?)";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
                preparedStatement3.setInt(1, idNewUser);
                preparedStatement3.setInt(2, user.getAddresses().get(0).getId());
                preparedStatement3.execute();

                String query4 = "INSERT INTO scool.users_telephone VALUES (?, ?)";
                PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                preparedStatement4.setInt(1, idNewUser);
                preparedStatement4.setInt(2, user.getTelephones().get(0).getId());
                preparedStatement4.execute();
                connection.commit();
            } else {
                System.out.println("User with login - " + user.getLogin() + " already exists in the database.");
            }
        } catch (SQLException throwable) {
            connection.rollback();
            throwable.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void deleteUserById(int id) throws SQLException {
        if (isExistUserById(id)) {
            Connection connection = ConnectionManager.getConnection();
            try {
                connection.setAutoCommit(false);
                String query = "DELETE FROM scool.users WHERE users.user_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();

                String query2 = "DELETE FROM scool.users_address WHERE users_address.users_id = ?";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setInt(1, id);
                preparedStatement2.execute();

                String query3 = "DELETE FROM scool.users_telephone WHERE users_telephone.user_id = ?";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
                preparedStatement3.setInt(1, id);
                preparedStatement3.execute();
                connection.commit();
            } catch (SQLException throwable) {
                connection.rollback();
                throwable.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } else {
            try {
                throw new IdNotFoundException(id);
            } catch (IdNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void deleteUserByLogin(String login) throws SQLException {
        if (isExistUserByLogin(login)) {
            Connection connection = ConnectionManager.getConnection();
            try {
                connection.setAutoCommit(false);
                String query = "SELECT * FROM scool.users WHERE users.login = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int idUserForDelete = resultSet.getInt("user_id");

                String query2 = "DELETE FROM scool.users WHERE scool.users.login = ?";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setString(1, login);
                preparedStatement2.execute();

                String query3 = "DELETE FROM scool.users_address WHERE users_address.users_id = ?";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
                preparedStatement3.setInt(1, idUserForDelete);
                preparedStatement3.execute();

                String query4 = "DELETE FROM scool.users_telephone WHERE users_telephone.user_id = ?";
                PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                preparedStatement4.setInt(1, idUserForDelete);
                preparedStatement4.execute();
                connection.commit();
            } catch (SQLException throwable) {
                connection.rollback();
                throwable.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } else {
            try {
                throw new LoginNotFoundException(login);
            } catch (LoginNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<User> findUsersByAddress(int addressId) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT users.user_id, users.login, users.password, t.telephone_id, t.number, a.address_id, a.street, a.home" +
                    " FROM scool.users" +
                    " LEFT JOIN scool.users_telephone ut on users.user_id = ut.user_id" +
                    " LEFT JOIN scool.telephone t on ut.telephone_id = t.telephone_id" +
                    " LEFT JOIN scool.users_address ua on users.user_id = ua.users_id" +
                    " LEFT JOIN scool.address a on ua.address_id = a.address_id" +
                    " WHERE ua.address_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, addressId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
                if (userList.contains(user)) {
                    userList.get(userList.indexOf(user)).getTelephones().add(new Telephone(
                            resultSet.getInt("telephone_id"),
                            resultSet.getInt("number")));
                } else {
                    user.getAddresses().add(new Address(
                            resultSet.getInt("address_id"),
                            resultSet.getString("street"),
                            resultSet.getString("home")));
                    user.getTelephones().add(new Telephone(
                            resultSet.getInt("telephone_id"),
                            resultSet.getInt("number")));
                    userList.add(user);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> findUsersByTelephone(int telephone) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT users.user_id, users.login, users.password, t.telephone_id, t.number, a.address_id, a.street, a.home" +
                    " FROM scool.users" +
                    " LEFT JOIN scool.users_telephone ut on users.user_id = ut.user_id" +
                    " LEFT JOIN scool.telephone t on ut.telephone_id = t.telephone_id" +
                    " LEFT JOIN scool.users_address ua on users.user_id = ua.users_id" +
                    " LEFT JOIN scool.address a on ua.address_id = a.address_id" +
                    " WHERE t.telephone_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, telephone);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
                if (userList.contains(user)) {
                    userList.get(userList.indexOf(user)).getAddresses().add(new Address(
                            resultSet.getInt("address_id"),
                            resultSet.getString("street"),
                            resultSet.getString("home")));
                } else {
                    user.getAddresses().add(new Address(
                            resultSet.getInt("address_id"),
                            resultSet.getString("street"),
                            resultSet.getString("home")));
                    user.getTelephones().add(new Telephone(
                            resultSet.getInt("telephone_id"),
                            resultSet.getInt("number")));
                    userList.add(user);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT users.user_id, users.login, users.password, t.telephone_id, t.number, a.address_id, a.street, a.home" +
                    " FROM scool.users" +
                    " LEFT JOIN scool.users_telephone ut on users.user_id = ut.user_id" +
                    " LEFT JOIN scool.telephone t on ut.telephone_id = t.telephone_id" +
                    " LEFT JOIN scool.users_address ua on users.user_id = ua.users_id" +
                    " LEFT JOIN scool.address a on ua.address_id = a.address_id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
                if (userList.contains(user)) {
                    Address address = new Address(
                            resultSet.getInt("address_id"),
                            resultSet.getString("street"),
                            resultSet.getString("home"));
                    if (!userList.get(userList.indexOf(user)).getAddresses().contains(address)) {
                        userList.get(userList.indexOf(user)).getAddresses().add(address);
                    }
                    Telephone telephone = new Telephone(
                            resultSet.getInt("telephone_id"),
                            resultSet.getInt("number"));
                    if (!userList.get(userList.indexOf(user)).getTelephones().contains(telephone)) {
                        userList.get(userList.indexOf(user)).getTelephones().add(telephone);
                    }
                } else {
                    user.getAddresses().add(new Address(
                            resultSet.getInt("address_id"),
                            resultSet.getString("street"),
                            resultSet.getString("home")));
                    user.getTelephones().add(new Telephone(
                            resultSet.getInt("telephone_id"),
                            resultSet.getInt("number")));
                    userList.add(user);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        if (isExistUserByLogin(login)) {
            try (Connection connection = ConnectionManager.getConnection()) {
                String query = "SELECT users.user_id, users.login, users.password, t.telephone_id, t.number, a.address_id, a.street, a.home" +
                        " FROM scool.users" +
                        " LEFT JOIN scool.users_telephone ut on users.user_id = ut.user_id" +
                        " LEFT JOIN scool.telephone t on ut.telephone_id = t.telephone_id" +
                        " LEFT JOIN scool.users_address ua on users.user_id = ua.users_id" +
                        " LEFT JOIN scool.address a on ua.address_id = a.address_id" +
                        " WHERE users.login = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
                user.getAddresses().add(new Address(
                        resultSet.getInt("address_id"),
                        resultSet.getString("street"),
                        resultSet.getString("home")));
                user.getTelephones().add(new Telephone(
                        resultSet.getInt("telephone_id"),
                        resultSet.getInt("number")));
                while (resultSet.next()) {
                    Address address = new Address(
                            resultSet.getInt("address_id"),
                            resultSet.getString("street"),
                            resultSet.getString("home"));
                    if (!user.getAddresses().contains(address)) {
                        user.getAddresses().add(address);
                    }
                    Telephone telephone = new Telephone(
                            resultSet.getInt("telephone_id"),
                            resultSet.getInt("number"));
                    if (!user.getTelephones().contains(telephone)) {
                        user.getTelephones().add(telephone);
                    }
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }else{
            try {
                throw new LoginNotFoundException(login);
            } catch (LoginNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return user;
    }

    @Override
    public User findUserById(int id) {
        User user = null;
        if (isExistUserById(id)) {
            try (Connection connection = ConnectionManager.getConnection()) {
                String query = "SELECT users.user_id, users.login, users.password, t.telephone_id, t.number, a.address_id, a.street, a.home" +
                        " FROM scool.users" +
                        " LEFT JOIN scool.users_telephone ut on users.user_id = ut.user_id" +
                        " LEFT JOIN scool.telephone t on ut.telephone_id = t.telephone_id" +
                        " LEFT JOIN scool.users_address ua on users.user_id = ua.users_id" +
                        " LEFT JOIN scool.address a on ua.address_id = a.address_id" +
                        " WHERE users.user_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
                user.getAddresses().add(new Address(
                        resultSet.getInt("address_id"),
                        resultSet.getString("street"),
                        resultSet.getString("home")));
                user.getTelephones().add(new Telephone(
                        resultSet.getInt("telephone_id"),
                        resultSet.getInt("number")));
                while (resultSet.next()) {
                    Address address = new Address(
                            resultSet.getInt("address_id"),
                            resultSet.getString("street"),
                            resultSet.getString("home"));
                    if (!user.getAddresses().contains(address)) {
                        user.getAddresses().add(address);
                    }
                    Telephone telephone = new Telephone(
                            resultSet.getInt("telephone_id"),
                            resultSet.getInt("number"));
                    if (!user.getTelephones().contains(telephone)) {
                        user.getTelephones().add(telephone);
                    }
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } else {
            try {
                throw new IdNotFoundException(id);
            } catch (IdNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return user;
    }

    @Override
    public List<String> findAllByName() {
        List<String> listNames = new ArrayList<>();
        String query = "SELECT users.login FROM scool.users";
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                listNames.add(resultSet.getString("login"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return listNames;
    }

    @Override
    public void addAddressForUser(int userId, int addressId) {
        try(Connection connection = ConnectionManager.getConnection()){
            String query = "INSERT INTO scool.users_address VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, addressId);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void addTelephoneForUser(int userId, int telephoneId) {
        try(Connection connection = ConnectionManager.getConnection()){
            String query = "INSERT INTO scool.users_telephone VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, telephoneId);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public boolean isExistUserById(int id) {
        boolean isExist = false;
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM scool.users WHERE users.user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExist = true;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return isExist;
    }

    @Override
    public boolean isExistUserByLogin(String login) {
        boolean isExist = false;
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM scool.users WHERE users.login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExist = true;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return isExist;
    }
}

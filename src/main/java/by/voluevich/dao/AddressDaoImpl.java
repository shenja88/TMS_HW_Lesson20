package by.voluevich.dao;

import by.voluevich.entity.Address;
import by.voluevich.service.exception.IdNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    @Override
    public List<Address> getAddresses() {
        List<Address> addressList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM scool.address";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                addressList.add(new Address(
                        resultSet.getInt("address_id"),
                        resultSet.getString("street"),
                        resultSet.getString("home")));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return addressList;
    }

    @Override
    public void updateAddressById(int id, Address address) {
        if (isExistAddressById(id)) {
            try (Connection connection = ConnectionManager.getConnection()) {
                String query = "UPDATE scool.address SET address.street = ?, address.home = ? WHERE address.address_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, address.getStreet());
                preparedStatement.setString(2, address.getHome());
                preparedStatement.setInt(3, id);
                preparedStatement.execute();
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
    }

    @Override
    public void addAddress(Address address) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "INSERT INTO scool.address(street, home) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getHome());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    private boolean isExistAddressById(int id) {
        boolean isExist = false;
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM scool.address WHERE address.address_id = ?";
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
}

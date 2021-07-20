package by.voluevich.dao;

import by.voluevich.entity.Telephone;
import by.voluevich.service.exception.IdNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelephoneDaoImpl implements TelephoneDao {

    @Override
    public List<Telephone> getTelephones() {
        List<Telephone> telephoneList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM scool.telephone";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                telephoneList.add(new Telephone(
                        resultSet.getInt("telephone_id"),
                        resultSet.getInt("number")));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return telephoneList;
    }

    @Override
    public void updateTelephone(int id, Telephone telephone) {
        if (isExistTelephoneById(id)) {
            try (Connection connection = ConnectionManager.getConnection()) {
                String query = "UPDATE scool.telephone SET telephone.number = ? WHERE telephone.telephone_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, telephone.getNumber());
                preparedStatement.setInt(2, id);
                preparedStatement.execute();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }else {
            try {
                throw new IdNotFoundException(id);
            } catch (IdNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void addTelephone(Telephone telephone) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "INSERT INTO scool.telephone(number) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, telephone.getNumber());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private boolean isExistTelephoneById(int id) {
        boolean isExist = false;
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM scool.telephone WHERE telephone.telephone_id = ?";
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

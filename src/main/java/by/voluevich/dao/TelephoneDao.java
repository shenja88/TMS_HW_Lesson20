package by.voluevich.dao;

import by.voluevich.entity.Telephone;

import java.util.List;

public interface TelephoneDao {

    List<Telephone> getTelephones();

    void updateTelephone(int id, Telephone telephone);

    void addTelephone(Telephone telephone);

}

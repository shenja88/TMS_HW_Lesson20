package by.voluevich.controllers;

import by.voluevich.entity.Telephone;

import java.util.List;

public interface TelephoneController {

    List<Telephone> getTelephones();

    void updateTelephone(int id, Telephone telephone);

    void addTelephone(Telephone telephone);

}

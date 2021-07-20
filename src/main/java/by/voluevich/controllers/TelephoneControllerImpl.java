package by.voluevich.controllers;

import by.voluevich.entity.Telephone;
import by.voluevich.service.utils.Dependencies;

import java.util.List;

public class TelephoneControllerImpl implements TelephoneController{

    @Override
    public List<Telephone> getTelephones() {
        return Dependencies.getTelephoneDao().getTelephones();
    }

    @Override
    public void updateTelephone(int id, Telephone telephone) {
        Dependencies.getTelephoneDao().updateTelephone(id, telephone);
    }

    @Override
    public void addTelephone(Telephone telephone) {
        Dependencies.getTelephoneDao().addTelephone(telephone);
    }
}

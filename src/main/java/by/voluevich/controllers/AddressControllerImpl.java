package by.voluevich.controllers;

import by.voluevich.entity.Address;
import by.voluevich.service.exception.IdNotFoundException;
import by.voluevich.service.utils.Dependencies;


import java.util.List;

public class AddressControllerImpl implements AddressController{

    @Override
    public List<Address> getAddresses() {
        return Dependencies.getAddressDao().getAddresses();
    }

    @Override
    public void updateAddressById(int id, Address address) {
        try {
            Dependencies.getAddressDao().updateAddressById(id, address);
        } catch (IdNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAddress(Address address) {
        Dependencies.getAddressDao().addAddress(address);
    }
}

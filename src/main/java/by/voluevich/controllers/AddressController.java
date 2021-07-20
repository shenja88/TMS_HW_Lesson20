package by.voluevich.controllers;

import by.voluevich.entity.Address;

import java.util.List;

public interface AddressController {

    List<Address> getAddresses();

    void updateAddressById(int id, Address address);

    void addAddress(Address address);


}

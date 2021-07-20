package by.voluevich.dao;

import by.voluevich.entity.Address;
import by.voluevich.service.exception.IdNotFoundException;

import java.util.List;

public interface AddressDao {

    List<Address> getAddresses();

    void updateAddressById(int id, Address address) throws IdNotFoundException;

    void addAddress(Address address);

}

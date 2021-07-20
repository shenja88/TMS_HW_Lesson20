package by.voluevich.model.button.address_menu;

import by.voluevich.entity.Address;
import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

public class UpdateAddressesButton implements ActionButton {
    @Override
    public String name() {
        return "Update address in data base.";
    }

    @Override
    public void tap() {
    int id = Input.getInt("Enter id of address to update.");
    Dependencies.getAddressController().updateAddressById(id, new Address(
            Input.getString("Enter street."),
            Input.getString("Enter number of home.")));
    }
}

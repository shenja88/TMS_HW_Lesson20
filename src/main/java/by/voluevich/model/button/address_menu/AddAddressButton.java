package by.voluevich.model.button.address_menu;

import by.voluevich.entity.Address;
import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

public class AddAddressButton implements ActionButton {


    @Override
    public String name() {
        return "Add address.";
    }

    @Override
    public void tap() {
        Dependencies.getAddressController().addAddress(new Address(
                Input.getString("Enter street."),
                Input.getString("Enter number of home.")));
    }
}

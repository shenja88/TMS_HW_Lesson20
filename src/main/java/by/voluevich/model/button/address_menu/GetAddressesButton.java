package by.voluevich.model.button.address_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;

public class GetAddressesButton implements ActionButton {
    @Override
    public String name() {
        return "View all address.";
    }

    @Override
    public void tap() {
        System.out.println(Dependencies.getAddressController().getAddresses());
    }
}

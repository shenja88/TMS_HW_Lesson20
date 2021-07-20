package by.voluevich.model.button.main_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.model.menu.AddressMenuImpl;

public class GetAddressMenuButton implements ActionButton {
    @Override
    public String name() {
        return "Address menu operation.";
    }

    @Override
    public void tap() {
        AddressMenuImpl addressMenu = new AddressMenuImpl();
        addressMenu.selectButton();
    }
}

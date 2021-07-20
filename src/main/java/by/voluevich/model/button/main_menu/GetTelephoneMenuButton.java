package by.voluevich.model.button.main_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.model.menu.TelephoneMenuImpl;

public class GetTelephoneMenuButton implements ActionButton {
    @Override
    public String name() {
        return "Telephone menu operation.";
    }

    @Override
    public void tap() {
        TelephoneMenuImpl telephoneMenu = new TelephoneMenuImpl();
        telephoneMenu.selectButton();
    }
}

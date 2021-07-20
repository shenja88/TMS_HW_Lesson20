package by.voluevich.model.button.main_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.model.menu.UserMenuImpl;

public class GetUserMenuButton implements ActionButton {
    @Override
    public String name() {
        return "User menu operation.";
    }

    @Override
    public void tap() {
        UserMenuImpl userMenu = new UserMenuImpl();
        userMenu.selectButton();
    }
}

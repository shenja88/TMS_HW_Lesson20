package by.voluevich.model.menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.exception.MenuItemNotFoundException;

import java.util.List;

public interface Menu {

    void getName();

    List<ActionButton> getMenu();

    void selectButton() throws MenuItemNotFoundException;
}

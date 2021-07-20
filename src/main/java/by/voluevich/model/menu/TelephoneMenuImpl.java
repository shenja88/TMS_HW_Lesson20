package by.voluevich.model.menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.model.button.BackButton;
import by.voluevich.model.button.telephone_menu.AddTelephoneButton;
import by.voluevich.model.button.telephone_menu.GetTelephonesButton;
import by.voluevich.model.button.telephone_menu.UpdateTelephoneButton;
import by.voluevich.service.exception.MenuItemNotFoundException;
import by.voluevich.service.utils.Input;

import java.util.Arrays;
import java.util.List;

public class TelephoneMenuImpl implements Menu {
    private List<ActionButton> buttonList = Arrays.asList(
            new BackButton(new MainMenu()),
            new AddTelephoneButton(),
            new GetTelephonesButton(),
            new UpdateTelephoneButton());

    @Override
    public void getName() {
        System.out.println("\n============ Telephone Menu ==========");
    }

    @Override
    public List<ActionButton> getMenu() {
        getName();
        return buttonList;
    }

    @Override
    public void selectButton() {
        List<ActionButton> list = getMenu();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + " - " + list.get(i).name());
        }
        int select;
        while (true) {
            select = Input.getInt("Select operation.");
            if (select < 1 || select > list.size()) {
                try {
                    throw new MenuItemNotFoundException();
                } catch (MenuItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                break;
            }
        }
        list.get(select - 1).tap();
    }
}


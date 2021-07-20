package by.voluevich.model.menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.model.button.BackButton;
import by.voluevich.model.button.address_menu.AddAddressButton;
import by.voluevich.model.button.address_menu.GetAddressesButton;
import by.voluevich.model.button.address_menu.UpdateAddressesButton;
import by.voluevich.service.exception.MenuItemNotFoundException;
import by.voluevich.service.utils.Input;

import java.util.Arrays;
import java.util.List;

public class AddressMenuImpl implements Menu {
    private List<ActionButton> buttonList = Arrays.asList(
            new BackButton(new MainMenu()),
            new AddAddressButton(),
            new GetAddressesButton(),
            new UpdateAddressesButton());

    @Override
    public void getName() {
        System.out.println("\n========== Address Menu =========");
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

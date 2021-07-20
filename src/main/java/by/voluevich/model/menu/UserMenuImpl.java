package by.voluevich.model.menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.model.button.BackButton;
import by.voluevich.model.button.user_menu.*;
import by.voluevich.service.exception.MenuItemNotFoundException;
import by.voluevich.service.utils.Input;

import java.util.Arrays;
import java.util.List;

public class UserMenuImpl implements Menu {
    private List<ActionButton> buttonList = Arrays.asList(
            new BackButton(new MainMenu()),
            new AddUserButton(),
            new AddAddressForUserButton(),
            new AddTelephoneForUserButton(),
            new DeleteUserByIdButton(),
            new DeleteUserByLoginButton(),
            new FindAllByNames(),
            new FindUserByIdButton(),
            new FindUserByLoginButton(),
            new FindUsersByAddressButton(),
            new FindUsersByTelephone(),
            new GetUsersButton(),
            new IsExistUserByLoginButton(),
            new IsExistUserByIdButton());

    @Override
    public void getName() {
        System.out.println("\n=========== User Menu ===========");
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


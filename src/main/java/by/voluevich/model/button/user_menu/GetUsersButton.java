package by.voluevich.model.button.user_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;

public class GetUsersButton implements ActionButton {
    @Override
    public String name() {
        return "View info of all users.";
    }

    @Override
    public void tap() {
        System.out.println(Dependencies.getUserController().getUsers());
    }
}

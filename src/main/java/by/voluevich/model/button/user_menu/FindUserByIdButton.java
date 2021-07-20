package by.voluevich.model.button.user_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

public class FindUserByIdButton implements ActionButton {
    @Override
    public String name() {
        return "Find user by ID.";
    }

    @Override
    public void tap() {
        System.out.println(Dependencies.getUserController().findUserById(Input.getInt("Enter ID of user for search.")));
    }
}

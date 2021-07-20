package by.voluevich.model.button.user_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

public class FindUserByLoginButton implements ActionButton {
    @Override
    public String name() {
        return "Find user by login.";
    }

    @Override
    public void tap() {
        System.out.println(Dependencies.getUserController().findUserByLogin(Input.getString("Enter login of user for search.")));
    }
}

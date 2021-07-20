package by.voluevich.model.button.user_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

import java.sql.SQLException;

public class DeleteUserByLoginButton implements ActionButton {
    @Override
    public String name() {
        return "Remove user by login.";
    }

    @Override
    public void tap() {
        try {
            Dependencies.getUserController().deleteUserByLogin(Input.getString("Enter login of user for remove."));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

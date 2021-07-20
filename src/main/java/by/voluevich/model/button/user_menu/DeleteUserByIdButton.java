package by.voluevich.model.button.user_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

import java.sql.SQLException;

public class DeleteUserByIdButton implements ActionButton {
    @Override
    public String name() {
        return "Remove user by ID.";
    }

    @Override
    public void tap() {
        try {
            Dependencies.getUserController().deleteUserById(Input.getInt("Enter ID of user for remove."));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

package by.voluevich.model.button.user_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

import java.sql.SQLException;

public class IsExistUserByLoginButton implements ActionButton {
    @Override
    public String name() {
        return "Check exist user in database by login.";
    }

    @Override
    public void tap() {
        try {
            String login = Input.getString("Enter login of user to search.");
            if(Dependencies.getUserController().isExistUserByLogin(login)){
                System.out.println("A user with login " + login + " - exists in the database");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

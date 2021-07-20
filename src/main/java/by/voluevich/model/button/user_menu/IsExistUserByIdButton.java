package by.voluevich.model.button.user_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

import java.sql.SQLException;

public class IsExistUserByIdButton implements ActionButton {
    @Override
    public String name() {
        return "Check exist user in database by id.";
    }

    @Override
    public void tap() {
        try {
            int id = Input.getInt("Enter ID of user to search.");
            if(Dependencies.getUserController().isExistUserById(id)){
                System.out.println("A user with ID " + id + " - exists in the database");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

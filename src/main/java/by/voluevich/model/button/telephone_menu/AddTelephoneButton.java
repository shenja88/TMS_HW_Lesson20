package by.voluevich.model.button.telephone_menu;

import by.voluevich.entity.Telephone;
import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

public class AddTelephoneButton implements ActionButton {
    @Override
    public String name() {
        return "Add telephone number.";
    }

    @Override
    public void tap() {
        Dependencies.getTelephoneController().addTelephone(new Telephone(
                Input.getInt("Enter number of telephone.")));
    }
}

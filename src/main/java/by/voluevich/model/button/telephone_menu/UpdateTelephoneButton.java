package by.voluevich.model.button.telephone_menu;

import by.voluevich.entity.Address;
import by.voluevich.entity.Telephone;
import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

public class UpdateTelephoneButton implements ActionButton {
    @Override
    public String name() {
        return "Update number of telephone in data base.";
    }

    @Override
    public void tap() {
        int id = Input.getInt("Enter id telephone to update.");
        Dependencies.getTelephoneController().updateTelephone(id, new Telephone(
                Input.getInt("Enter number of telephone.")));
    }
}

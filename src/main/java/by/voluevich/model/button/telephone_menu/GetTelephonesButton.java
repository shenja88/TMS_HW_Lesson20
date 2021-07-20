package by.voluevich.model.button.telephone_menu;

import by.voluevich.model.button.ActionButton;
import by.voluevich.service.utils.Dependencies;

public class GetTelephonesButton implements ActionButton {
    @Override
    public String name() {
        return "View all numbers of telephones.";
    }

    @Override
    public void tap() {
        System.out.println(Dependencies.getTelephoneController().getTelephones());
    }
}

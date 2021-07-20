package by.voluevich.model.button.user_menu;

import by.voluevich.entity.Telephone;
import by.voluevich.model.button.ActionButton;
import by.voluevich.service.exception.MenuItemNotFoundException;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

import java.util.List;

public class FindUsersByTelephone implements ActionButton {
    @Override
    public String name() {
        return "Find users by telephone.";
    }

    @Override
    public void tap() {
        List<Telephone> listAllTel = Dependencies.getTelephoneController().getTelephones();
        int selectNum;
        while (true) {
            for (int i = 0; i < listAllTel.size(); i++) {
                System.out.println((i + 1) + " - " + listAllTel.get(i).getNumber());
            }
            selectNum = Input.getInt("Select a phone number.");
            if(selectNum < 1 || selectNum > listAllTel.size()){
                try {
                    throw new MenuItemNotFoundException();
                } catch (MenuItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                break;
            }
        }
        System.out.println(Dependencies.getUserController().findUsersByTelephone(listAllTel.get(selectNum - 1).getId()));
    }
}

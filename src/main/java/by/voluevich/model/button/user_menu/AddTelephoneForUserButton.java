package by.voluevich.model.button.user_menu;

import by.voluevich.entity.Address;
import by.voluevich.entity.Telephone;
import by.voluevich.entity.User;
import by.voluevich.model.button.ActionButton;
import by.voluevich.service.exception.MenuItemNotFoundException;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

import java.util.List;

public class AddTelephoneForUserButton implements ActionButton {
    @Override
    public String name() {
        return "Add number of telephone for user.";
    }

    @Override
    public void tap() {
        List<User> userList = Dependencies.getUserController().getUsers();
        int selectUser;
        for(int i = 0; i < userList.size(); i++){
            System.out.println((i + 1) + " - " + userList.get(i).getLogin());
        }
        while (true){
            selectUser = Input.getInt("Select user.");
            if(selectUser < 1 || selectUser > userList.size()){
                try {
                    throw new MenuItemNotFoundException();
                } catch (MenuItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                break;
            }
        }

        List<Telephone> telephoneList = Dependencies.getTelephoneController().getTelephones();
        int selectTelephone;
        for(int i = 0; i < telephoneList.size(); i++){
            System.out.println((i + 1) + " - " + telephoneList.get(i).getNumber());
        }
        while (true){
            selectTelephone = Input.getInt("Select user.");
            if(selectTelephone < 1 || selectTelephone > telephoneList.size()){
                try {
                    throw new MenuItemNotFoundException();
                } catch (MenuItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                break;
            }
        }
        Dependencies.getUserController().addTelephoneForUser(
                userList.get(selectUser - 1).getId(),
                telephoneList.get(selectTelephone - 1).getId());
    }
}

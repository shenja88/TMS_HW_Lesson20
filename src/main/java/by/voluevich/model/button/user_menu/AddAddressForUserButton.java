package by.voluevich.model.button.user_menu;

import by.voluevich.entity.Address;
import by.voluevich.entity.User;
import by.voluevich.model.button.ActionButton;
import by.voluevich.service.exception.MenuItemNotFoundException;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

import java.util.List;

public class AddAddressForUserButton implements ActionButton {

    @Override
    public String name() {
        return "Add address for user.";
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

        List<Address> addressList = Dependencies.getAddressController().getAddresses();
        int selectAddress;
        for(int i = 0; i < addressList.size(); i++){
            System.out.println((i + 1) + " - " + addressList.get(i).getStreet() + ", " + addressList.get(i).getHome());
        }
        while (true){
            selectAddress = Input.getInt("Select user.");
            if(selectAddress < 1 || selectAddress > addressList.size()){
                try {
                    throw new MenuItemNotFoundException();
                } catch (MenuItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                break;
            }
        }
        Dependencies.getUserController().addAddressForUser(
                userList.get(selectUser - 1).getId(),
                addressList.get(selectAddress - 1).getId());
    }
}

package by.voluevich.model.button.user_menu;

import by.voluevich.entity.Address;
import by.voluevich.model.button.ActionButton;
import by.voluevich.service.exception.MenuItemNotFoundException;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

import java.util.List;

public class FindUsersByAddressButton implements ActionButton {
    @Override
    public String name() {
        return "Find users by address.";
    }

    @Override
    public void tap() {
        List<Address> addressList = Dependencies.getAddressController().getAddresses();
        int selectNum;
        while (true) {
            for (int i = 0; i < addressList.size(); i++) {
                System.out.println((i + 1) + " - " + addressList.get(i).getStreet() + ", " + addressList.get(i).getHome());
            }
            selectNum = Input.getInt("Select address.");
            if (selectNum < 1 || selectNum > addressList.size()) {
                try {
                    throw new MenuItemNotFoundException();
                } catch (MenuItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                break;
            }
        }
        System.out.println(Dependencies.getUserController().findUsersByAddress(addressList.get(selectNum - 1).getId()));
    }
}
